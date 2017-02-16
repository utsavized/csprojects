3DMaze
======

Gameplay and Objective
This is a 3D Maze game that gives the user the ability to start from a NXN maze. The objective of this game is to collect a certain number of spheres in a given amount of time to proceed to the next level. There are a total of 18 Levels. If the user fails to collect the required number of spheres in the allotted time, the level simply restarts. The user can try as many times as he/she wants.

The game uses texture mapping to create a dungeon-like environment. The total time spent in a level is made to represent an entire day. Hence, there is a sun, which is associated to the countdown timer. The sun rises as the countdown timer begins and sets as the counter ends.

Startup Options

The user will chose a NXN maze to begin the game from a menu as soon as the program starts. The screen will also have a ‘Navigation On/Off’ option, which will either turn on or turn off a small navigation map on the bottom left corner of the playing screen. The map will have a green arrow representing the user and white dots representing the spheres to be collected.

Navigation

The user will use the keyboard (arrow keys Up, Down, Left and Right) to navigate. The game does not have complex movements, only simple movements made smoother using timer function and linear interpolation. Hence, user can only move backward, forward or turn 90 degrees in either direction. For instance, pressing the Up and Left keys simultaneously will not make the user move forward at 45 degrees as that would be a complex movement.
The user can also use the Page Up and Page Down keys along with Left and Right keys to get and overview of the maze.
On-screen Display
The user will have a number of displays on-screen. Like mentioned earlier, if the navigation map is turned on, it will be displayed at the bottom left corner of the screen. The top left corner will have the score tally i.e. the number of spheres collected and the number of spheres left to be collected. The top center of the screen will have the countdown timer, which is an example of OpenGL animation. While in the game, the user can also right click at anytime to display a menu, which has the Restart and the Exit options.

Functions

1.  A 3D perspective view

The 3D view has been created using the gluPerspective and gluLookAt functions utilizing the GL_PROJECTION and GL_MODELVIEW matrices.
------------------------------------------------------------------------------------------------------------------------
glMatrixMode(GL_PROJECTION);
glLoadIdentity();
gluPerspective(120, 1, 0.25, 100);
glMatrixMode(GL_MODELVIEW);
glLoadIdentity();
.
.
.
//Linearly interpolate eye coordinates from current cell to next cell during viewer movement
float eyex = int_pol(progress, current_x, next_x);
float eyey = current_y;
float eyez = int_pol(progress, current_z, next_z);

//Linearly interpolate view vector from current cell to next cell during viewer rotation
float viewx = int_pol(progress, dx[current_dir], dx[next_dir]);
float viewy = 0;
float viewz = int_pol(progress, dz[current_dir], dz[next_dir]);
.
.
.
//gluLookAt for inside maze
gluLookAt(eyex, eyey, eyez, eyex + viewx, eyey + viewy, eyez + viewz, 0, 1, 0);


2.	User interaction involving both the keyboard and the mouse.

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

//If movement allowed, start_movement from current position to position        if(allowed && collected_spheres < max_spheres) 
	 {
            next_x = x1;
            next_z = z1;
            start_movement();
        }

		//Check if a sphere is navigated
		if (current_x == sphere_x && current_z == sphere_z)
		{
			collected_spheres++;//Update the number of collected spheres

			//Check if the viewer has collected all spheres on the level
			if (collected_spheres >= max_spheres)
			{
				//Shift to the next level
				collected_spheres = 0;//Reset the number of collected spheres to zero
				
				//Check for the maze size limit
				if (maze_xsize < 20 && maze_zsize < 20)
				{
					max_spheres++;//More spheres for next level
					maze_xsize++;//Larger maze for next level
					maze_zsize++;	//Larger maze for next level
				}

				build_maze();//Rebuild the maze for next level
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
.
.
.
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
.
.
.
glutCreateMenu(main_menu);
glutAddMenuEntry("Restart", 1);
glutAddMenuEntry("Exit", 2);
	
glutReshapeFunc(reshapefunc_entry);
glutDisplayFunc(displayfunc_entry);
glutKeyboardFunc(keyboardfunc);
glutMouseFunc(mousefunc_entry);

glutAttachMenu(GLUT_RIGHT_BUTTON);


3.	An animated object using double buffering.

//Draws timer countdown at the top of the screen
void draw_countdown()
{
	glPushMatrix();

	glLoadIdentity();
	glTranslatef(0, 0.45, -0.35);//Top of the screen
	glScalef(0.0005, 0.0005, 0.2);

	glColor3f(1, 0, 0);//Use red color

	//Calculate digits of countdown value
	int digit1 = countdown / 100 % 10;//First  digit of countdown value
	int digit2 = countdown / 10  % 10;//Second digit of countdown value
	int digit3 = countdown / 1   % 10;//Third  digit of countdown value

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
.
.
.
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
			collected_spheres = 0;// reset collected spheres to zero
			build_maze();// rebuild the maze
		}

		//Call this function again in 1 second
		glutTimerFunc(1000, timerfunc, 0);
	}
	glutPostRedisplay();
}


4.	A texture-mapped object.

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
.
.
.
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

	glDisable(GL_TEXTURE_2D);

	glDisable(GL_LIGHTING);
}
.
.
.
//Quadratic object for texture mapping of the sun
GLUquadric *qobj = gluNewQuadric(); 

gluQuadricTexture(qobj,GL_TRUE); 

glEnable(GL_TEXTURE_2D);
glBindTexture(GL_TEXTURE_2D,sunTex);

glPushMatrix();
glTranslatef(sun_pos[0], sun_pos[1], sun_pos[2]);
gluSphere(qobj,1.0,16,16); 
glPopMatrix();

gluDeleteQuadric(qobj); 
glDisable(GL_TEXTURE_2D);


5.	A semi-transparent object created by using alpha blending.

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


6.	Realistic coloring that utilizes lighting and shading.

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
.
.
.
//Enable flashlight as a light source
glEnable(GL_LIGHT0);
glLightfv(GL_LIGHT0, GL_DIFFUSE, flashlight_color);
glLightfv(GL_LIGHT0, GL_SPECULAR, flashlight_color);
glLightfv(GL_LIGHT0, GL_POSITION, flashlight_pos);
glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 45);
glLightf(GL_LIGHT0, GL_SPOT_EXPONENT, 90);
glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 0.07);
glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 0.07);
.
.
.
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

	glDisable(GL_TEXTURE_2D);

	glDisable(GL_LIGHTING);
}
7.	Start the program with a two-dimensional menu giving the user options to customize your program.
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
.
.
.
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
			place_random_sphere();

			//Switch to maze mode
			glutDisplayFunc(displayfunc_maze);
			glutMouseFunc(mousefunc_maze);

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


8.	Allow the user to choose from multiple viewing options while in the program.
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
