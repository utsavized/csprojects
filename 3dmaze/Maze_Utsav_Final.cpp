/*
Programmer: Utsav Pandey
Date written: 12/1/2008
Last modified: 12/6/2008
Description: Builds random maze with selected requirements
*/

#include <fstream>
#include <iostream>
#include <math.h>
#include <GL/glut.h>
#include<windows.h>
using namespace std;


int countdown;										//Current countdown value
int max_countdown;									//Initial countdown value, depends on the maze size


//Flashlight properties
GLfloat flashlight_color[] = {1, 1, 1, 1};
GLfloat flashlight_pos[] = {0, 0, 0, 1};

//Sun properties
GLfloat sun_color[] = {0.7, 0.7, 0.7, 1};
GLfloat sun_pos[] = {0, 0, 0, 1};

//Material properties
GLfloat floor_color[] = {0.8, 0.8, 0.8, 0.8};
GLfloat exterior_color[] = {1, 1, 1, 1};
GLfloat sphere_color[] = {0.8, 0.8, 0.8, 1};
GLfloat specular_color[] = {0.6, 0.6, 0.6, 1};
GLfloat shine = 10;

//Variables to identify different textures
GLint wallTex, floorTex, sphereTex, sunTex;

int num_texture=-1; //Counter to keep track of the last loaded texture

//Wall & direction numbers
#define ZPOS 0									//Positive z direction
#define XNEG 1									//Negative x direction
#define ZNEG 2									//Negative z direction
#define XPOS 3									//Positive x direction

int map = 0;										//Select if navigation is on

//Variables to check if the maze has been solved
int complete_x=0;
int complete_z=0;
int check_exit=0;


int opposite[4] = {ZNEG, XPOS, ZPOS, XNEG};		//Gives opposite direction
int dx[4] = {0, -1, 0, +1};						//X-coordinate change depending on the direction
int dz[4] = {+1, 0, -1, 0};						//Z-coordinate change depending on the direction

struct Cell 
{
	GLfloat color[4];							//Color to use for interior walls of the cell
	int walls[4];								//Flags indicating presence of walls (ZPOS/XNEG/ZNEG/XPOS)
	int visited;								//Cell status during building of the maze
};

Cell cells[20][20];								//Reserved space for a maze of up to 20 by 20 cells

int maze_xsize = 0;								//Actual size of a maze in x dimension, can be changed
int maze_zsize = 0;								//Actual size of a maze in z dimension, can be changed

struct Position 
{												//Coordinates of a cell to store in a stack
	int x;
	int z;
};

Position stack[400];							//Reserved stack space for up to 20 by 20 cell positions
int stack_pushed = 0;							//Actual number of elements pushed onto a stack

int current_x = 0;								//Current x position of the viewer in the maze
int current_y = 0;								//Current y position of the viewer in the maze
int current_z = 0;								//Current z position of the viewer in the maze

int next_x = current_x;							// next x position of the viewer in the maze during movement
int next_z = current_z;							// next z position of the viewer in the maze during movement

int current_dir = ZNEG;							//Current viewing direction of the viewer
int next_dir = current_dir;						// next viewing direction of the viewer during rotation

int max_spheres = 0;							//Number of spheres on a level
int collected_spheres = 0;						//Number of collected spheres so far
int sphere_x = -1;								//X position of the sphere
int sphere_z = -1;								//Z position of the sphere

int moving_state = 0;							// flag indicating movement/rotation (1) or standing still (0)
float progress = 0;								// progress of time (from 0 to 1) during viewer movement/rotation


float int_pol(float t, float a, float b)
{
    //Linear interpolation from a to b
    return a + (b - a) * t;
}

/**** Stack Functions ****/

//Checks if stack is empty
int stack_empty()
{
	return stack_pushed == 0;
}

//Pushes position onto the stack
void push_stack(Position pos)
{
	int stack_max = 400;

	//Check if stack is not full
	if (stack_pushed < stack_max)
	{
		stack[stack_pushed] = pos;
		stack_pushed++;
	}
}

//Pops position off the stack
Position pop_stack()
{
	//Check if there are any positions on the stack
	if (!stack_empty())
	{
		stack_pushed--;
		return stack[stack_pushed];
	}

	//Stack is empty, return default position
	Position pos = {0, 0};
	return pos;
}

//Loads bitmaps for texture mapping
int LoadBitmap(char *filename)
{
	int i, j=0; //Index variables
	FILE *l_file; //File pointer
	unsigned char *l_texture; //The pointer to the memory zone in which we will load the texture

	// windows.h gives us these types to work with the Bitmap files
	BITMAPFILEHEADER fileheader; 
	BITMAPINFOHEADER infoheader;
	RGBTRIPLE rgb;

	num_texture++; // The counter of the current texture is increased

	if( (l_file = fopen(filename, "rb"))==NULL) return (-1); // Open the file for reading

	fread(&fileheader, sizeof(fileheader), 1, l_file); // Read the fileheader

	fseek(l_file, sizeof(fileheader), SEEK_SET); // Jump the fileheader
	fread(&infoheader, sizeof(infoheader), 1, l_file); // and read the infoheader

	// Now we need to allocate the memory for our image (width * height * color deep)
	l_texture = (byte *) malloc(infoheader.biWidth * infoheader.biHeight * 4);
	// And fill it with zeros
	memset(l_texture, 0, infoheader.biWidth * infoheader.biHeight * 4);

	// At this point we can read every pixel of the image
	for (i=0; i < infoheader.biWidth*infoheader.biHeight; i++)
	{            
		// We load an RGB value from the file
		fread(&rgb, sizeof(rgb), 1, l_file); 

		// And store it
		l_texture[j+0] = rgb.rgbtRed; // Red component
		l_texture[j+1] = rgb.rgbtGreen; // Green component
		l_texture[j+2] = rgb.rgbtBlue; // Blue component
		l_texture[j+3] = 255; // Alpha value
		j += 4; // Go to the next position
	}

	fclose(l_file); // Closes the file stream

	glBindTexture(GL_TEXTURE_2D, num_texture); // Bind the ID texture specified by the 2nd parameter

	// The next commands sets the texture parameters
	// If the u,v coordinates overflow the range 0,1 the image is repeated
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR); // The magnification function ("linear" produces better results)
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST); //The minifying function

	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE); // We don't combine the color with the original surface color, use only the texture map.
	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);

	// Finally we define the 2d texture
	glTexImage2D(GL_TEXTURE_2D, 0, 4, infoheader.biWidth, infoheader.biHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, l_texture);

	// And create 2d mipmaps for the minifying function
	gluBuild2DMipmaps(GL_TEXTURE_2D, 4, infoheader.biWidth, infoheader.biHeight, GL_RGBA, GL_UNSIGNED_BYTE, l_texture);

	free(l_texture); // Free the memory we used to load the texture

	return (num_texture); // Returns the current texture OpenGL ID
}

/**** Creation Functions ****/

//Builds the maze at the start or at the level reset/completion
void build_maze()
{
	//Initialize cells
	for (int z = 0; z < maze_zsize; z++)
	{
		for (int x = 0; x < maze_xsize; x++)
		{
			//Assign color for each cell
			cells[z][x].color[0] = 0.8;
			cells[z][x].color[1] = 0.8;
			cells[z][x].color[2] = 0.8;
			cells[z][x].color[3] = 1;

			//Start with all 4 walls present
			cells[z][x].walls[ZPOS] = 1;
			cells[z][x].walls[XNEG] = 1;
			cells[z][x].walls[ZNEG] = 1;
			cells[z][x].walls[XPOS] = 1;

			//Mark each cell as unvisited
			cells[z][x].visited = 0;
		}
	}

	//Select and visit a random cell to start at
	int x0 = rand() % maze_xsize;
	int z0 = rand() % maze_zsize;
	cells[z0][x0].visited = 1;
	
	//Build a maze by removing some walls
	while (1)
	{
		//Collect cell's unvisited neighbors to a list

		int unvisited[4];								//List of directions (ZPOS/XNEG/ZNEG/XPOS) to unvisited neighbors
		int n = 0;										//Actual number of the cell's unvisited neighbors

		//Check 4 directions to each neighbor
		for (int dir = 0; dir < 4; dir++)
		{
			int x1 = x0 + dx[dir];						//X coordinate of a neighbor
			int z1 = z0 + dz[dir];						//Z coordinate of a neighbor

			if ((x1 >= 0 && x1 < maze_xsize))			//Check x bounds
			{
				if ((z1 >= 0 && z1 < maze_zsize))		//Check z bounds
				{
					if (cells[z1][x1].visited == 0)		//Check if neighbor is unvisited
					{
						unvisited[n++] = dir;			//Add this direction to unvisited list
					}
				}
			}

		}

		//Check if unvisited neighbors are found
		if (n > 0)
		{
			int selected = rand() % n;					//Select random neighbor from unvisited list
			int dir1 = unvisited[selected];				//Direction from current cell to selected neighbor
			int dir2 = opposite[dir1];					//Direction from selected neighbor to current cell (opposite)
			int x1 = x0 + dx[dir1];						//X coordinate of selected neighbor
			int z1 = z0 + dz[dir1];						//Z coordinate of selected neighbor
			cells[z0][x0].walls[dir1] = 0;				//Remove a wall of current cell in the direction to selected neighbor
			cells[z1][x1].walls[dir2] = 0;				//Remove a wall of selected neighbor in the direction to current cell
			cells[z1][x1].visited = 1;					//Visit selected neighbor

			//Push current position onto the stack
			Position pos = {x0, z0};
			push_stack(pos);

			//Set current position to neighbor position
			x0 = x1;
			z0 = z1;
		}
		else
		{
			//Unvisited neighbors are not found, so ZPOStrack to the previously visited cell

			//If stack is empty, we have visited all unvisited cells
			if (stack_empty())
				break;

			//Otherwise pop previously visited cell off the stack
			Position pos = pop_stack();

			//And continue processing this cell on the next iteration of this loop
			x0 = pos.x;
			z0 = pos.z;
		}

	}

	//Remove the middle wall of near side of the maze for an entrance
	int entrance_x = maze_xsize / 2;
	int entrance_z = maze_zsize - 1;
	cells[entrance_z][entrance_x].walls[ZPOS] = 0;

	//Remove the middle of far side of the maze for an exit
	int exit_x = maze_xsize / 2;
	complete_x=exit_x;
	int exit_z = 0;
	complete_z=exit_z;
	cells[exit_z][exit_x].walls[ZNEG] = 0;

	//Set the viewer outside the maze facing the entrance
	current_x = maze_xsize / 2;						//Current x position of the viewer in the maze
	current_z = maze_zsize;							//Current z position of the viewer in the maze
	current_dir = ZNEG;								//Current viewing direction of the viewer

	//Reset countdown to a number of seconds depending on the maze size
	max_countdown = maze_xsize * 25;
	countdown = max_countdown;

	//Set max number of spheres depending on the maze size
	max_spheres = maze_xsize-1;
}


//Places sphere at the random location in the maze
void place_random_sphere()
{
	//Set random location for a sphere
	//If it coincides with current viewer location, try again
	do
	{
		sphere_x = rand() % maze_xsize;
		sphere_z = rand() % maze_zsize;
	} while (sphere_x == current_x && sphere_z == current_z);
}

/**** Drawing Functions ****/

//Draws square quad for walls/floors
void draw_square()
{
	//Wall/floor subdivision level
	//Can be increased to improve lighting smoothness
	//Can be decreased to improve performance
	int n = 3;

	//Subdivide square into n by n quads for smooth lighting
	glBegin(GL_QUADS);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			float tx0 =float (j) / n;
			float ty0 =float (i) / n;
			float tx1 =float (j + 1) / n;
			float ty1 =float (i + 1) / n;
			float x0 = tx0 - 0.5;
			float x1 = tx1 - 0.5;
			float y0 = ty0 - 0.5;
			float y1 = ty1 - 0.5;
			
			//Map the texture into the squares
			glTexCoord2f(x0,y0);
			glVertex3f(x0, y0, 0);
			glTexCoord2f(x1,y0);
			glVertex3f(x1, y0, 0);  
			glTexCoord2f(x1,y1);
			glVertex3f(x1, y1, 0);
			glTexCoord2f(x0,y1);
			glVertex3f(x0, y1, 0);
		}
	}
	glEnd();
}

//Draw Text on the screen
void draw_text(char * text)
{
    glScalef(0.2, -0.2, 1);

    for(; *text; text++) {
        glutStrokeCharacter(GLUT_STROKE_ROMAN, *text);
    }
}

//Draw the numbers on screen
void draw_number(int number)
{
    glScalef(0.1, -0.1, 1);

    if(number >= 10) glutStrokeCharacter(GLUT_STROKE_ROMAN, '0' + number / 10);
    glutStrokeCharacter(GLUT_STROKE_ROMAN, '0' + number % 10);
}

//Draws timer countdown at the top of the screen
void draw_countdown()
{
	glPushMatrix();

	glLoadIdentity();
	glTranslatef(0, 0.45, -0.35);					//Top of the screen
	glScalef(0.0005, 0.0005, 0.2);

	glColor3f(1, 0, 0);								//Use red color

	//Calculate digits of countdown value
	int digit1 = countdown / 100 % 10;				//First  digit of countdown value
	int digit2 = countdown / 10  % 10;				//Second digit of countdown value
	int digit3 = countdown / 1   % 10;				//Third  digit of countdown value

	//Draw first digit if it's not leading zero
	if (countdown >= 100)
		glutStrokeCharacter(GLUT_STROKE_ROMAN, '0' + digit1);

	//Draw second digit if it's not leading zero
	if (countdown >= 10)
		glutStrokeCharacter(GLUT_STROKE_ROMAN, '0' + digit2);

	//Draw third digit always
	glutStrokeCharacter(GLUT_STROKE_ROMAN, '0' + digit3);

	glPopMatrix();
}

//Draws current score at the top-left corner of the screen
void draw_score()
{
	//Draw a row of little spheres
	for (int i = 0; i < max_spheres; i++)
	{
		glPushMatrix();

		glLoadIdentity();
		glTranslatef(-0.5 + i * 0.05, 0.55, -0.35);	//Top-left corner of the screen
		glScalef(0.2, 0.2, 0.01);

		if (i < collected_spheres)
			glColor3f(1.0, 1.0, 1.0);				//Draw collected spheres as white circles
		else
			glColor3f(0.2, 0.2, 0.2);				//Draw remaining spheres as dark circles

		glutSolidSphere(0.1, 16, 16);

		glPopMatrix();
	}
}


//Draws maze walls and floors
void draw_maze()
{
	glMaterialfv(GL_FRONT, GL_SPECULAR, specular_color);
	glMaterialf(GL_FRONT, GL_SHININESS, shine);

	glEnable(GL_LIGHTING);

	int x = 0, z = 0;

	//Draw cell walls
	for (z = 0; z < maze_zsize; z++)
	{
		for (x = 0; x < maze_xsize; x++)
		{
			glEnable(GL_TEXTURE_2D);

			glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, floor_color);

			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ZERO);
			glBindTexture(GL_TEXTURE_2D, wallTex);

			glPushMatrix();

			glTranslatef(x, 0, z);

			//Draw back wall if present
			if (cells[z][x].walls[ZPOS])
			{
				glPushMatrix();

				glRotatef(180, 0, 1, 0);
				glTranslatef(0, 0, -0.499);
				glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, cells[z][x].color);
				glNormal3f(0, 0, 1);
				draw_square();

				//If at border of the maze, also draw exterior-facing wall
				if (z == maze_zsize - 1)
				{
					glTranslatef(0, 0, -0.002);
					glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, exterior_color);
					glNormal3f(0, 0, -1);
					draw_square();
				}

				glPopMatrix();
			}

			//Draw left wall if present
			if (cells[z][x].walls[XNEG])
			{
				glPushMatrix();

				glRotatef(90, 0, 1, 0);
				glTranslatef(0, 0, -0.499);
				glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, cells[z][x].color);
				glNormal3f(0, 0, 1);
				draw_square();

				//If at border of the maze, also draw exterior-facing wall
				if (x == 0)
				{
					glTranslatef(0, 0, -0.002);
					glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, exterior_color);
					glNormal3f(0, 0, -1);
					draw_square();
				}

				glPopMatrix();
			}

			//Draw front wall if present
			if (cells[z][x].walls[ZNEG])
			{
				glPushMatrix();

				glTranslatef(0, 0, -0.499);
				glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, cells[z][x].color);
				glNormal3f(0, 0, 1);
				draw_square();

				//If at border of the maze, also draw exterior-facing wall
				if (z == 0)
				{
					glTranslatef(0, 0, -0.002);
					glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, exterior_color);
					glNormal3f(0, 0, -1);
					draw_square();
				}

				glPopMatrix();
			}

			//Draw right wall if present
			if (cells[z][x].walls[XPOS])
			{
				glPushMatrix();

				glRotatef(-90, 0, 1, 0);
				glTranslatef(0, 0, -0.499);
				glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, cells[z][x].color);
				glNormal3f(0, 0, 1);
				draw_square();

				//If at border of the maze, also draw exterior-facing wall
				if (x == maze_xsize - 1)
				{
					glTranslatef(0, 0, -0.002);
					glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, exterior_color);
					glNormal3f(0, 0, -1);
					draw_square();
				}

				glPopMatrix();
			}

			glPopMatrix();
		}

	}

	//Draw cell floors
	glEnable(GL_TEXTURE_2D);

	glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, floor_color);

	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ZERO);
	glBindTexture(GL_TEXTURE_2D, floorTex);

	for (z = 0 - 1; z < maze_zsize + 1; z++)
	{
		for (x = 0 - 1; x < maze_xsize + 1; x++)
		{
			glPushMatrix();
			glTranslatef(x, -0.5, z);
			glRotatef(90, -1, 0, 0);
			glNormal3f(0, 0, 1);
			draw_square();
			glPopMatrix();
		}
	}
	
	glDisable(GL_BLEND);
	glDisable(GL_TEXTURE_2D);
	glDisable(GL_LIGHTING);
}

//Draws 2D navigation map in the bottom-left corner of the screen
void draw_navigation_map()
{
	glDisable(GL_DEPTH_TEST);

	//Reset projection matrix
	glMatrixMode(GL_PROJECTION);
	glPushMatrix();
	glLoadIdentity();

	//Set 2D projection matrix so navigation map takes about 1/4 of screen size
	int s = maze_xsize;
	if (maze_zsize > s) s = maze_zsize;
	gluOrtho2D(-1, s * 4 - 1, maze_zsize + 1, maze_zsize + 1 - s * 4);

	//Reset modelview matrix
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();
	glLoadIdentity();

	//Use yellow color for navigation map
	glColor3f(1, 1, 0);

	//Draw walls as lines on mavigation map
	glBegin(GL_LINES);
	for (int z = 0; z < maze_zsize; z++)
	{
		for (int x = 0; x < maze_xsize; x++)
		{
			//Draw back wall if present
			if (cells[z][x].walls[ZPOS])
			{
				glVertex2i(x, z + 1); glVertex2i(x + 1, z + 1);
			}

			//Draw left wall if present
			if (cells[z][x].walls[XNEG])
			{
				glVertex2i(x, z); glVertex2i(x, z + 1);
			}

			//Draw front wall if present
			if (cells[z][x].walls[ZNEG])
			{
				glVertex2i(x, z); glVertex2i(x + 1, z);
			}

			//Draw right wall if present
			if (cells[z][x].walls[XPOS])
			{
				glVertex2i(x + 1, z); glVertex2i(x + 1, z + 1);
			}
		}
	}
	glEnd();

	//Draw player on the map as a green arrow
	glColor3f(0, 1, 0);

	glPushMatrix();

	glTranslatef(current_x + 0.5, current_z + 0.5, 0);

	glBegin(GL_LINES);

	//Arrow is facing back
	if (current_dir == ZPOS)
	{
		glVertex2f(0, +0.25); glVertex2f(0, -0.25);		//Arrow line
		glVertex2f(0, +0.25); glVertex2f(-0.25, 0);		//Arrow head
		glVertex2f(0, +0.25); glVertex2f(+0.25, 0);		//Arrow head
	}

	//Arrow is facing left
	if (current_dir == XNEG)
	{
		glVertex2f(-0.25, 0); glVertex2f(+0.25, 0);		//Arrow line
		glVertex2f(-0.25, 0); glVertex2f(0, +0.25);		//Arrow head
		glVertex2f(-0.25, 0); glVertex2f(0, -0.25);		//Arrow head
	}

	//Arrow is facing front
	if (current_dir == ZNEG)
	{
		glVertex2f(0, -0.25); glVertex2f(0, +0.25);		//Arrow line
		glVertex2f(0, -0.25); glVertex2f(+0.25, 0);		//Arrow head
		glVertex2f(0, -0.25); glVertex2f(-0.25, 0);		//Arrow head
	}

	//Arrow is facing right
	if (current_dir == XPOS)
	{
		glVertex2f(+0.25, 0); glVertex2f(-0.25, 0);		//Arrow line
		glVertex2f(+0.25, 0); glVertex2f(0, -0.25);		//Arrow head
		glVertex2f(+0.25, 0); glVertex2f(0, +0.25);		//Arrow head
	}

	glEnd();

	glPopMatrix();

	//Draw sphere on the map as a white dot
	glColor3f(1, 1, 1);
	glPointSize(4);
	glBegin(GL_POINTS);
	glVertex2f(sphere_x + 0.5, sphere_z + 0.5);
	glEnd();

	//Restore projection matrix
	glMatrixMode(GL_PROJECTION);
	glPopMatrix();

	//Restore modelview matrix
	glMatrixMode(GL_MODELVIEW);
	glPopMatrix();

	glEnable(GL_DEPTH_TEST);
}


//Draws sphere
void draw_sphere()
{
	glEnable(GL_LIGHTING);

	glMaterialfv(GL_RIGHT, GL_AMBIENT_AND_DIFFUSE, sphere_color);
	glMaterialfv(GL_RIGHT, GL_SPECULAR, specular_color);
	glMaterialf(GL_RIGHT, GL_SHININESS, shine);

	//Quadratic Object for texture mapping into sphere
	GLUquadric *qobj = gluNewQuadric(); 
	gluQuadricTexture(qobj,GL_TRUE); 

	glEnable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	glBindTexture(GL_TEXTURE_2D,sphereTex);

	glTranslatef(sphere_x, -0.25, sphere_z);
	gluSphere(qobj,0.25,16,16); 

	gluDeleteQuadric(qobj); 

	glDisable(GL_BLEND);
	glDisable(GL_TEXTURE_2D); 
	glDisable(GL_LIGHTING);
}

/**** GLUT callbacks ****/

//Timer function callback
void timerfunc(int ignored)
{
	if (countdown > 0)
	{
		countdown--;

		//Check if countdown reaches 0
		if (countdown <= 0)
		{
			//Restart level
			collected_spheres = 0;			// reset collected spheres to zero
			build_maze();					// rebuild the maze
		}

		//Call this function again in 1 second
		glutTimerFunc(1000, timerfunc, 0);
	}
	glutPostRedisplay();
}

//Reshape function callback for entry mode
void reshapefunc_entry(int width, int height)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, width, height, 0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	glViewport(0, 0, width, height);

	glutPostRedisplay();
}

//Reshape function callback for Maze mode
void reshapefunc_maze(int width, int height)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glViewport(0, 0, width, height);
	gluPerspective(120,width/(double)height,0.25,100);
	glMatrixMode(GL_MODELVIEW);
	

}

//Display function callback for maze mode
void displayfunc_maze()
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(120, 1, 0.25, 100);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	glShadeModel(GL_SMOOTH);
	glEnable(GL_DEPTH_TEST);
	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);


	glClearColor(0, 0, 0, 1);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	//Linearly interpolate eye coordinates from current cell to next cell during viewer movement
    float eyex = int_pol(progress, current_x, next_x);
    float eyey = current_y;
    float eyez = int_pol(progress, current_z, next_z);

    //Linearly interpolate view vector from current cell to next cell during viewer rotation
    float viewx = int_pol(progress, dx[current_dir], dx[next_dir]);
    float viewy = 0;
    float viewz = int_pol(progress, dz[current_dir], dz[next_dir]);


	//Enable flashlight as a light source
	glEnable(GL_LIGHT0);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, flashlight_color);
	glLightfv(GL_LIGHT0, GL_SPECULAR, flashlight_color);
	glLightfv(GL_LIGHT0, GL_POSITION, flashlight_pos);
	glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 45);
	glLightf(GL_LIGHT0, GL_SPOT_EXPONENT, 90);
	glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 0.07);
	glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 0.07);

	//gluLookAt for inside maze
	gluLookAt(eyex, eyey, eyez, eyex + viewx, eyey + viewy, eyez + viewz, 0, 1, 0);
	
	//Calculate progress of level completion in the range 0..1
	GLfloat progress = 1 - GLfloat(countdown) / max_countdown;

	//Calculate angle to the sun in radians depending on progress
	GLfloat angle = progress * 3.14159;

	//Calculate radius of sun orbit depending on the maze size
	GLfloat radius = maze_xsize * 10;

	//Calculate position of the sun
	sun_pos[0] = radius * cos(angle);
	sun_pos[1] = radius * sin(angle);
	sun_pos[2] = maze_zsize / 2;
	sun_pos[3] = 1;

	//Enable sun as a light source
	glEnable(GL_LIGHT1);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, sun_color);
	glLightfv(GL_LIGHT1, GL_SPECULAR, sun_color);
	glLightfv(GL_LIGHT1, GL_POSITION, sun_pos);
	
	//Quadratic object for texture mapping of the sun
	GLUquadric *qobj = gluNewQuadric(); 

	gluQuadricTexture(qobj,GL_TRUE); 

	glEnable(GL_TEXTURE_2D);
	glDepthMask(GL_FALSE);
	glBindTexture(GL_TEXTURE_2D,sunTex);

	glPushMatrix();
	glTranslatef(sun_pos[0], sun_pos[1], sun_pos[2]);
	gluSphere(qobj,1.0,16,16); 
	glPopMatrix();

	gluDeleteQuadric(qobj); 
	glDepthMask(GL_TRUE);
	glDisable(GL_TEXTURE_2D); 

	draw_maze();

	draw_sphere();

	//If map is on
	if(map == 1)
	{
		draw_navigation_map();
		draw_countdown();
		draw_score();
	}

	//If map is off
	else if(map == 0)
	{
		draw_countdown();
		draw_score();
	}

	//Swap back and front buffers for double buffering to work
	glutSwapBuffers();
}

void timerfuncMove(int timer_number)
{
    //Timer_number ranges from 1 to 30 during callback calls,
    //so calculate movement/rotation progress from it
    progress = (float)timer_number / 30;

    //Check if this is last timer callback
    if(timer_number == 30) {
        moving_state = 0;							//End movement/rotation animation
        current_x = next_x;							//Update viewer
        current_z = next_z;
        current_dir = next_dir;
    }
    glutPostRedisplay();
}


//Start movement to make them movement smoother using interpolation & timer function
void start_movement()
{
    //Enable moving state to prevent keyboard handling during movement/rotation animation
    moving_state = 1;
    progress = 0;

    //For smooth animation of movement/rotation,
    //Register 30 timers to be triggered in consecutive intervals
	if(collected_spheres < max_spheres)
	{
		for(int number = 1; number <= 30; number++)
		{	
			glutTimerFunc(number * 10, timerfuncMove, number);  // this will call myTimer callback 30 times every 10 milliseconds
		}	
	}
}

//Keyboard Function
void keyboardfunc(unsigned char key, int x, int y)
{
	if(key == 27) 
		exit(0);

    glutPostRedisplay();
}

//Special Keyboard function for handling keyboard arrow keys
void specialfunc(int key, int x, int y)
{
    //Prevent keyboard handling during movement/rotation animation
    if(moving_state) return;

    //Rotate the viewer 90 degrees to the left
    if(key == GLUT_KEY_LEFT) {
        if(current_dir == ZPOS) next_dir = XPOS;
        if(current_dir == XNEG) next_dir = ZPOS;
        if(current_dir == ZNEG) next_dir = XNEG;
        if(current_dir == XPOS) next_dir = ZNEG;
        start_movement();
    }

    //Rotates the viewer 90 degrees to the right
    if(key == GLUT_KEY_RIGHT) {
        if(current_dir == ZPOS) next_dir = XNEG;
        if(current_dir == XNEG) next_dir = ZNEG;
        if(current_dir == ZNEG) next_dir = XPOS;
        if(current_dir == XPOS) next_dir = ZPOS;
        start_movement();
    }

    if(key == GLUT_KEY_UP || key == GLUT_KEY_DOWN) {
        //Current position in the maze
        int x0 = current_x;
        int z0 = current_z;

        //Forward/backward direction of movement
        int moving_dir = current_dir;
        if(key == GLUT_KEY_DOWN) 
			moving_dir = opposite[current_dir];

        //Position to move to
        int x1 = current_x + dx[moving_dir];
        int z1 = current_z + dz[moving_dir];

        //Flag indicating if a movement is allowed
        int allowed = 1;

        //Check if the viewer is inside the maze
        if(x0 >= 0 && x0 < maze_xsize && z0 >= 0 && z0 < maze_zsize) 
		{
            //Check if a wall is on the way
            if(cells[z0][x0].walls[moving_dir]) 
				allowed = 0;
        }

        //Check if the viewer is entering the maze
        if(x1 >= 0 && x1 < maze_xsize && z1 >= 0 && z1 < maze_zsize) 
		{
            //Check if a wall is on the way
            if(cells[z1][x1].walls[opposite[moving_dir]]) 
				allowed = 0;
        }

        //Check if the viewer is going too far from the maze
        if(x1 < -1 || x1 > maze_xsize || z1 < -1 || z1 > maze_zsize) 
		{
            allowed = 0;
        }

        //If movement allowed, start_movement from current position to position (x1, z1)
        if(allowed && collected_spheres < max_spheres) 
		{
            next_x = x1;
            next_z = z1;
            start_movement();
        }

		//Check if a sphere is navigated
		if (current_x == sphere_x && current_z == sphere_z)
		{
			collected_spheres++;					//Update the number of collected spheres

			//Check if the viewer has collected all spheres on the level
			if (collected_spheres >= max_spheres)
			{
				//Shift to the next level
				collected_spheres = 0;				//Reset the number of collected spheres to zero
				
				//Check for the maze size limit
				if (maze_xsize < 20 && maze_zsize < 20)
				{
					max_spheres++;					//More spheres for next level
					maze_xsize++;					//Larger maze for next level
					maze_zsize++;					//Larger maze for next level
				}

				build_maze();						//Rebuild the maze for next level
			}
			place_random_sphere();
		}

		//Check if maze is completed
		if (current_x==complete_x && current_z+1==complete_z)
		{
			check_exit=1;
		}
    }

    //Go to angle zoom mode
    if(key == GLUT_KEY_PAGE_UP) 
	{
        current_y++;
    }

    //Return from angle zoom mode
    if(key == GLUT_KEY_PAGE_DOWN) 
	{
        if(current_y > 0) 
			current_y--;
    }

    glutPostRedisplay();
}

//Display function callback for entry mode
void displayfunc_entry()
{
    glDisable(GL_DEPTH_TEST);
    glClearColor(0.5, 0.6, 0.6, 1);
    glClear(GL_COLOR_BUFFER_BIT);

    glColor3f(0, 0, 0);

    //Draw "X Size" text
    glPushMatrix();
    glTranslatef(60, 54 - 1 * 20, 0);
    draw_text("X Size");
    glPopMatrix();

    //Draw "Z Size" text
    glPushMatrix();
    glTranslatef(220, 54 - 1 * 20, 0);
    draw_text("Z Size");
    glPopMatrix();

    //Draw possible maze sizes
    for(int i = 1; i < 18; i++) 
	{
        for(int j = 0; j < 2; j++) 
		{
            int x0 = 60 + j * 160;
            int x1 = 145 + j * 160;
            int y0 = 40 + i * 20;
            int y1 = 56 + i * 20;
            glColor3f(0.8, 0.8, 0.8);

            if(j == 0 && maze_xsize == i + 1) 
				glColor3f(0.8, 0.6, 0.6);
            if(j == 1 && maze_zsize == i + 1) 
				glColor3f(0.8, 0.6, 0.6);

            glRecti(x0, y0, x1, y1);

            glPushMatrix();
            glTranslatef(x0 + 40, 54 + i * 20, 0);
            glColor3f(0, 0, 0);
            draw_number(i + 1);
            glPopMatrix();
        }
    }

    //Draw OK button
    int x0 = 400;
    int x1 = 480;
    int y0 = 440;
    int y1 = 480;
    glColor3f(0.8, 0.8, 0.8);
    glRecti(x0+45, y0+8, x1, y1-8);

    //Draw "OK" text
    glColor3f(0, 0, 0);
    glPushMatrix();
    glTranslatef(x0 + 45, y0 + 30, 0);
    draw_text("OK");
    glPopMatrix();

	//Draw EXIT button
    glColor3f(0.8, 0.8, 0.8);
    glRecti(x0-20, y0+8, x1-50, y1-8);

    //Draw EXIT text
    glColor3f(0, 0, 0);
    glPushMatrix();
    glTranslatef(x0 - 20, y0 + 30, 0);
    draw_text("EXIT");
    glPopMatrix();

	//Draw Navigation Prompt
	glColor3f(0, 0, 0);
	glPushMatrix();
    glTranslatef(x0 -350, y0 + 30, 0);
    draw_text("Navigation Map");
    glPopMatrix();

	//Draw Nav On button
    glColor3f(0.8, 0.8, 0.8);
	if(map==1)
		glColor3f(0.8, 0.6, 0.6);
    glRecti(x0-150, y0+8, x1-195, y1-8);

	//Draw Navigation On Prompt
	glColor3f(0, 0, 0);
	glPushMatrix();
    glTranslatef(x0 -150, y0 + 30, 0);
    draw_text("ON");
    glPopMatrix();

	//Draw Nav Off button
    glColor3f(0.8, 0.8, 0.8);
    if(map==0)
		glColor3f(0.8, 0.6, 0.6);
    glRecti(x0-100, y0+8, x1-130, y1-8);

	//Draw Navigation Off Prompt
	glColor3f(0, 0, 0);
	glPushMatrix();
    glTranslatef(x0 -100, y0 + 30, 0);
    draw_text("OFF");
    glPopMatrix();

    glColor3f(1, 1, 1);

   	//Swap back and front buffers for double buffering to work
	glutSwapBuffers();
}

//Mouse function callback for entry mode
void mousefunc_entry(int button, int state, int x, int y)
{
    if(button == GLUT_LEFT_BUTTON) 
	{
        //Check if number buttons are clicked
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 2; j++) {
                int x0 = 60 + j * 160;
                int x1 = 145 + j * 160;
                int y0 = 40 + i * 20;
                int y1 = 56 + i * 20;
                if(x >= x0 && x < x1 && y >= y0 && y < y1) {
                    if(j == 0) 
					{
                        maze_xsize = i + 1;
                    }
                    else 
					{
                        maze_zsize = i + 1;
                    }
                }
            }
        }

		//Check for Navigation Map Options
		if(x >= 250 && x < 285 && y >= 440+8 && y < 480-8)
			map=1;										//Map on

		if(x >= 300 && x < 350 && y >= 440+8 && y < 480-8)
			map=0;										//Map Off

        //Check if OK button is clicked
        if(x >= 400+45 && x < 480 && y >= 440+8 && y < 480-8) 
		{
            build_maze();
			glutReshapeFunc(reshapefunc_maze);
			place_random_sphere();

			//Switch to maze mode
			glutDisplayFunc(displayfunc_maze);
			glutMouseFunc(NULL);

			glutSpecialFunc(specialfunc);

			//Start countdown
			glutTimerFunc(1000, timerfunc, 0);
        }
		
		//Check if EXIT button is clicked
        if(x >= 400-20 && x < 480-50 && y >= 440+8 && y < 480-8) 
			exit(0);
	}
    glutPostRedisplay();
}

//Menu function
void main_menu(int index)
{
	switch(index)
	{
		case(1):
		{
			collected_spheres = 0;
			build_maze();
			glutReshapeFunc(reshapefunc_maze);
			glutDisplayFunc(displayfunc_maze);
			glutMouseFunc(NULL);
			break;
		}
		case(2):
		{
			exit(0);
			break;
		}
	}
}

//Main function
void main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);

	//Error checking
	if (maze_xsize < 2) maze_xsize = 2;
	if (maze_zsize < 2) maze_zsize = 2;
	if (maze_xsize > 20) maze_xsize = 20;
	if (maze_zsize > 20) maze_zsize = 20;

	glutInitWindowSize(500, 500);
	glutCreateWindow("Maze");

	glutCreateMenu(main_menu);
	glutAddMenuEntry("Restart",1);
	glutAddMenuEntry("Exit", 2);
	
	
	glutReshapeFunc(reshapefunc_entry);
	glutDisplayFunc(displayfunc_entry);
	glutKeyboardFunc(keyboardfunc);
	glutMouseFunc(mousefunc_entry);

	glutAttachMenu(GLUT_RIGHT_BUTTON);

	wallTex = LoadBitmap("stonewall.bmp");
	floorTex= LoadBitmap ("floor.bmp");
	sphereTex = LoadBitmap ("fire.bmp");
	sunTex = LoadBitmap ("sun.bmp");

	glutMainLoop();
}