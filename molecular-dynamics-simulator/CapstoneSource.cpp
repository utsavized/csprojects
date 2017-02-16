#include<iostream>
#include<fstream>
#include<string>
#include<time.h>
#include<math.h>
#include<list>
using namespace std;

const int MAX = 17;
const int MAXPART = 1024;

double initiatedBy; // (user/gui)
double	numOfParticles;
double sizeOfBox;
double	temp;
double	potentialFlag; // 0: hard sphere 1:square well 2:Lennord Jones
double	cyclesBtwSaves;
double	totalNumOfCycles;
double	currentCycle;
double	energyTop;
double	energyBottom;
double	lamda;
double	sigma;
double	epsilon;
double	continuation;
int moleculeInfoBeginsAt;

double particle[MAXPART][3];


double inputData[MAX];
void readInputData(void);
void printInputData(void);
void writeOutputData(void);
void pickparticle(int, int);
void initialize(void);
void calcHardShpereEnergy(double randNum, double randX, double randY, double randZ);
void calcSquareWellEnergy(double randNum, double randX, double randY, double randZ);
void calcLennardJonesEnergy(double randNum, double randX, double randY, double randZ);

void main(void)
{           
	readInputData();
	printInputData();
	writeOutputData();
	initialize();

	int temp;
	cout<<"\n\n\n";
	cin>>temp;
}

void readInputData(void)
{	/*
	[InitiatedBy] = (user/gui)
	[NumOfParticles] =
	[SizeOfBox]=
	[Temp] =
	[PotentialFlag]= 0: hard sphere 1:square well 2:Lennord Jones
	[CyclesBtwSaves]=
	[TotalNumOfCycles]=
	[currentCycle]=
	[energyTop] =
	[energyBottom] =
	[lamda]=
	[sigma]=
	[epsilon]=
	[Continuation] = //this willbe flagged if the program has atom information as well as energy information(its being loaded from a saved file)
	* Additional MoleculeInformation *
	*/
	string line;
	int equalIndex = 0, index = 0, lineNumber = 0;
	ifstream myInputFile;
	myInputFile.open("TryFile.txt");

	if(myInputFile.is_open() && index < MAX)
	{         
		//cout<<"Input file opened successfully.";
		while(!myInputFile.eof())
		{
			myInputFile>>line;
			lineNumber = lineNumber + 1;
			double lineLength = line.size();
			for (int i = 0; i < lineLength; i++){
				lineNumber = lineNumber + 1;
				if (line[i] ==   '='){ //if (line[i] == "=")
					equalIndex = i + 2;
				}
			}
			//substr(position, lenght)
			line = line.substr(equalIndex, line.length() - equalIndex);

			inputData[index] = atoi(line.c_str());
			index++;
		}
		myInputFile.close();
		continuation = (double) inputData[13];
		if (continuation == 1)
			moleculeInfoBeginsAt = (int) lineNumber;
		cout<<"\nInput file read successfully.";
	}
	else
		cout<<"Unable to Open the file.";
}

void printInputData(void)
{
	cout<<"\n\n";
	cout<<"Number of particles: "<<inputData[0];
	cout<<"\nSize of box: "<<inputData[1]<<" X "<<inputData[2]<<" X "<<inputData[3];
	cout<<"\nTemperature: "<<inputData[4];
	cout<<"\nPotential: "<<inputData[5];
	cout<<"\nCycles between saves: "<<inputData[6];
	cout<<"\nTotal cycles: "<<inputData[7];
	cout<<"\nEnergy Top: "<<inputData[8];
	cout<<"\nEnergy Bottom: "<<inputData[9];
	cout<<"\nLamda: "<<inputData[10];
	cout<<"\nSigma: "<<inputData[11];
	cout<<"\nElipson: "<<inputData[12];
	cout<<"\nAtomic Number: "<<inputData[13];
	cout<<"\nStarting Coordinates: "<<inputData[14]<<" "<<inputData[15]<<" "<<inputData[16];
}

void writeOutputData(void)
{
	int index=0;
	ofstream writeOutputFile;
	writeOutputFile.open("OutputFile.txt");

	if(writeOutputFile.is_open()&&index<MAX)
	{
		while(index<MAX)
		{

			writeOutputFile<<inputData[index]<<"\n";
			index++;

		}
		cout<<"\n\nOutput file opened successfully.";
		cout<<"\nOutput file written successfully.";
		writeOutputFile.close();
	}
	else
		cout<<"Unable to Open the file.";
}

void initialize(void)
{

	//	InitiatedBy = (string) inputData[0];
	//	NumOfParticles = (int) inputData[1];
	//	sizeOfBox = (double) inputData[2];
	//	Temperture = (double) inputData[3];
	//	PotentialFlag = (double) inputData[4];
	//	CyclesBtwSaves = (int) inputData[5];
	//	TotalNumOfCycles = (int) inputData[6];
	//	currentCycle = (int) inputData[7];
	//	energyTop = (double) inputData[8];
	//	energyBottom = (double) inputData[9];
	//	lamda = (double) inputData[10];
	//	sigma = (double) inputData[11];
	//	epsilon = (double) inputData[12];
	//	continuation = (bool) inputData[13];

	double addFactor = sizeOfBox/pow(numOfParticles,(1/3))-1;
	int particleNumber=1;

	double i, j, k;
	for(i=0; i<=sizeOfBox; i +=addFactor)
	{     
		for(j=0; j<=sizeOfBox; j +=addFactor)
		{
			for(k=0; j<=sizeOfBox; k +=addFactor)
			{
				particle[particleNumber][0]=i;
				particle[particleNumber][1]=j;
				particle[particleNumber][2]=k;
				particleNumber++;

			}
		}
	}
}

void pickParticle()//(double particle[][], double numOfParticles)
{
	int randNum;
	double randX;
	double randY;
	double randZ;

	bool acceptanceFlag = false;

	srand(time(NULL));      
	randNum = rand() % (int) numOfParticles;
	originalXValue = particle[randNum][0];
	originalYValue = particle[randNum][1];
	originalZValue = particle[randNum][2];

	randX= rand()%1000/999.000+(rand()%10);
	randY= rand()%1000/999.000+(rand()%10);
	randZ= rand()%1000/999.000+(rand()%10);

	if (PotentialFlag == 1){ // Hard Sphere Potential
		deltaE = calculateHardSphereEnergy(randNum, origionalXValue, origionalYValue, origionalZValue) - 
			calculateHardSphereEnergy(randNum, randX, randY, randZ);

		
	}
	else if(potentialFlag == 2){
		deltaE = calculateSquareWellEnergy(randNum, origionalXValue, origionalYValue, origionalZValue) - 
			calculateSquareWellEnergy(randNum, randX, randY, randZ);
	}
	else if (potentialFlag == 3){
		deltaE = calculateLennardJonesEnergy(randNum, origionalXValue, origionalYValue, origionalZValue) - 
			calculateLennardJonesEnergy(randNum, randX, randY, randZ);
	}



	do
	{
		calcHardSphereEnergy(randNum, randX, randY, randZ);
		//		acceptanceFlag = calcEnergy(randNum, randX, randY, randZ);
	}while(acceptanceFlag == false);


}


void calcHardSphereEnergy(double randNum, double randX, double randY, double randZ)
{
	int i, j, r = 0;
	double initialDeltaX, initialDeltaY,initialDeltaZ;
	double energyHS;
	double sumationEnergy = 0;
	bool infinate = false;
	for (i = 0; i < numOfParticles; i++){
		for (j = 0; j < numOfParticles - 1; j++){
			initialDeltaX = pow((particle[i][0] - partical[j][0]),2);
			initialDeltaY = pow((particle[i][1] - partical[j][1]),2);
			initialDeltaZ = pow((particle[i][2] - partical[j][2]),2);
			r = sqrt(initialDeltaX+initialDeltaY+initialDeltaZ);

			if (r > rcut){
				energyHS = 0;
			}
			else if (r <= rcut){
				return -10000000.0;

			}
			sumationEnergy += energyHS;

		}
	}
	return sumationEnergy;
}


void calcSquareWellEnergy(double randNum, double randX, double randY, double randZ){
	int i, j, r = 0;
	double initialDeltaX, initialDeltaY,initialDeltaZ;
	double energyOfRs;
	double sumationEnergy = 0;
	for (i = 0; i < numOfParticles; i++){
		for (j = 0; j < numOfParticles - 1; j++){
			initialDeltaX = pow((particle[i][0] - partical[j][0]),2);
			initialDeltaY = pow((particle[i][1] - partical[j][1]),2);
			initialDeltaZ = pow((particle[i][2] - partical[j][2]),2);
			r = sqrt(initialDeltaX+initialDeltaY+initialDeltaZ);

			if (r > sigma * lamda){
				energyOfRs = 0;
			}
			else if((sigma <= r) && (r <= (sigma * lamda))){
				energyOfRs = 0 - epsilon;
			}
			else if(r < sigma){
				return -10000000.0;
			}
			sumationEnergy += energyOfRs;
		}
	}
	return sumationEnergy;
}


void calcLennardJonesEnergy(double randNum, double randX, double randY, double randZ){
	int i, j, r = 0;
	double initialDeltaX, initialDeltaY,initialDeltaZ;
	double energyLJ;
	double sumationEnergy = 0;
	
	for (i = 0; i < numOfParticles; i++){
		for (j = 0; j < numOfParticles - 1; j++){
			initialDeltaX = pow((particle[i][0] - partical[j][0]),2);
			initialDeltaY = pow((particle[i][1] - partical[j][1]),2);
			initialDeltaZ = pow((particle[i][2] - partical[j][2]),2);
			r = sqrt(initialDeltaX+initialDeltaY+initialDeltaZ);

			energyLJ = 4 * epsilon ((pow(sigma/r),12) - (pow(sigma/r),6));
		}
		sumationEnergy += energyLJ;
	}
	return sumationEnergy;
}
