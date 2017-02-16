###
'''
Tic Tac Toe in Python
(using minimax /w alpha beta pruning)
Created on Feb 9, 2011
@author: Utsav Pandey (upandey@luc.edu)
'''
###


'''
Board Manupulations
'''
class Board:
    '''
    Constructor
    '''
    def __init__(self, boardV):
        if boardV == "":
            self.intBoard = [0,0,0,0,0,0,0,0,0]
        else:
            self.intBoard = boardV
        
        self.intMoveSymbol = 2  #0=empty;1=Player1;2=Player2
        self.moveCount = 0
    
    '''
    Python Lacks a arraycopy like Java, so had to write this
    '''
    def arraycopy(self, source, sourcepos, dest, destpos, numelem):
        dest[destpos:destpos+numelem] = source[sourcepos:sourcepos+numelem]
        
    '''
    Copy method, very shallow copy, unlike copy() that can be used by importing thc copy lib
    '''
    def copy(self, board):
        self.moveCount = board.moveCount
        self.intMoveSymbol = board.intMoveSymbol
        Board.arraycopy(self, board.intBoard, 0, self.intBoard, 0, 9)
    
    '''
    Decide which player's turn it is to move
    '''
    def SetMove(self, boxNo):
        if self.intBoard[boxNo] != 0:
            return False
        if self.intMoveSymbol == 1:
            self.intMoveSymbol = 2
        else:
            self.intMoveSymbol = 1
        self.intBoard[boxNo] = self.intMoveSymbol
        self.moveCount = self.moveCount + 1
        return True
    
    '''
    Check game state -- for loss, win, or draw
    '''
    def checkGameState(self):
        #Check game for row 1
        if self.intBoard[0]==self.intBoard[1] and self.intBoard[1]==self.intBoard[2] and self.intBoard[2]!=0:
            return self.intBoard[0]
        #Check game for row 2
        elif self.intBoard[3]==self.intBoard[4] and self.intBoard[4]==self.intBoard[5] and self.intBoard[5]!=0:
            return self.intBoard[3]
        #Check game for row 3
        elif self.intBoard[6]==self.intBoard[7] and self.intBoard[7]==self.intBoard[8] and self.intBoard[8]!=0:
            return self.intBoard[6]
        #Check game for column 1
        elif self.intBoard[0]==self.intBoard[3] and self.intBoard[3]==self.intBoard[6] and self.intBoard[6]!=0:
            return self.intBoard[0]
        #Check game for column 2
        elif self.intBoard[1]==self.intBoard[4] and self.intBoard[4]==self.intBoard[7] and self.intBoard[7]!=0:
            return self.intBoard[1]
        #Check game for column 3
        elif self.intBoard[2]==self.intBoard[5] and self.intBoard[5]==self.intBoard[8] and self.intBoard[8]!=0:
            return self.intBoard[2]
        #Check game for diagonal left to right
        elif self.intBoard[0]==self.intBoard[4] and self.intBoard[4]==self.intBoard[8] and self.intBoard[8]!=0:
            return self.intBoard[0]
        #Check game for diagonal right to left
        elif self.intBoard[2]==self.intBoard[4] and self.intBoard[4]==self.intBoard[6] and self.intBoard[6]!=0:
            return self.intBoard[2]
        else:
            #Check if any empty space is left in the board
            for x in range(0, 9):
                if self.intBoard[x]==0:
                    return 0    #No End State; Continue game
        return -1    #Draw
    
    '''
    Print the board with formatting
      0 | 1 | 2 
     ---+---+---
      3 | 4 | 5 
     ---+---+---
      6 | 7 | 8 
    '''
    
    def Print(self):
        print ''
        for x in range(0,3):
            p = ""
            for y in range (0,3):
                if y < 3 and y != 0:
                    p = p + "|" #print ("|")
                if self.intBoard[y+(3*x)] != 0:
                    if self.intBoard[y+(3*x)] == 1:
                        p = p + " X " #print (" X ")
                    else:
                        p = p + " O " #print (" O ")
                else:
                    p = p + " " + str(y + (3*x)) + " " # print ( " ", (y+(3*x)), " ")
            print p
            if x<2:
                print ("---+---+---")

'''
Tree Manipulations
'''       
class Node:
    '''
    Constructor
    Had to use n because python lacks overloaded constructors
    '''
    def __init__(self, n, pBoard):
        if n == 0:
            self.board = Board("")
        else:
            self.board = pBoard
        self.childrenNode = []
        self.parent = 0
        self.point = 0
        self.moveBox = 0
    
    '''
    Add Children
    '''
    def AddChildren(self, node):
        self.childrenNode.append(node)
        node.Parent(self)
    
    '''
    Declare parent
    '''
    def Parent(self, node):
        self.parent = node
    
    '''
    Copy method, very shallow copy, unlike copy() that can be used by importing thc copy lib
    '''
    def copy(self, n):
        self.point = n.point
        self.board.copy(n.board)
        
    '''
    Print, only used for debugging
    '''
    def Print(self):
        print ()
        print ("Point" + self.point)
        print ("moveBox" + self.moveBox)
        self.board.Print()
        print ()

'''
AI - Alpha Beta Pruning Algorithm
Unbeatable mode
'''
class Intelligence:
    '''
    Constructor
    '''
    def __init__(self, gBoard):
        self.gBoard = gBoard
        self.rootNode = Node(1,gBoard) 
        self.nodeCount = 0
    
    '''
    Make a move, initiate apha-beta
    ''' 
    def Move(self):
        max = -10
        bestNode = Node(0,"")
        for x in range(0,9):
            n = Node(0,"")
            n.copy(self.rootNode)
            if n.board.SetMove(x) == True:
                self.rootNode.AddChildren(n)
                n.moveBox = x
                val = self.alphaBeta(n, True, -10, 10)
                if val >= max:
                    max = val
                    bestNode = n
        return bestNode.moveBox
    
    '''
    Alpha beta algo
    '''
    def alphaBeta(self, node, min, alpha, beta):
        #if(game over in current board position)
        if self.boardPoint(node) != -2:
            node.point = self.boardPoint(node)
            return self.boardPoint(node)    #return winner
                
        else:    
            #if(min's turn)
            if min == True:
                #for each child
                for x in range(0,9): 
                    n = Node(0,"")
                    n.copy(node)
                    if n.board.SetMove(x) == True:
                        node.AddChildren(n)
                        n.moveBox = x
                        #score = alpha-beta(child,other player,alpha,beta)
                        val = self.alphaBeta(n, False, alpha, beta)
                        #if score < beta then beta = score (opponent has found a better worse move)
                        if val < beta:
                            beta = val
                            n.parent.point = val
                        
                #return beta (this is the opponent's best move)
                return beta
            
            #if(max's turn)
            elif min == False:
                #for each child
                for x in range(0,9):
                    n = Node(0,"")
                    n.copy(node)
                    if n.board.SetMove(x) == True:
                        node.AddChildren(n)
                        n.moveBox = x
                        #score = alpha-beta(child,other player,alpha,beta)
                        val = self.alphaBeta(n, True, alpha, beta)
                        #if score > alpha then alpha = score (we have found a better best move)
                        if val > alpha:
                            alpha = val
                            n.parent.point = val
                        
                #return alpha (this is our best move)
                return alpha
        
        return -100 #Just to satisfy the return type; this will never be reached
    
    '''
    Set marker in the board
    '''
    def boardPoint(self, n):
        if n.board.checkGameState() == 1:
            return -1
        
        if n.board.checkGameState() == 2:
            return 1
        
        if n.board.checkGameState() == -1:
            return 0
        
        return -2

'''
Player attributes
'''  
class Player:
    '''
    Constructor
    '''
    def __init__(self, playerName):
        self.playerName = playerName
        self.playerWin = 0
        self.playerLoss = 0
    
    '''
    Record player win
    '''
    def playerWin(self):
        self.playerWin = self.playerWin + 1
        
    '''
    Record player loss
    '''
    def playerLoss(self):
        self.playerLoss = self.playerLoss + 1
    
    '''
    Return player win count
    '''
    def playerWinCount(self):
        return self.playerWin
    
    '''
    Return player loss count
    '''
    def playerLossCount(self):
        return self.playerLoss
    
    '''
    In case player2 is AI, initiate next move for AI
    '''
    def NextMove(self,gBoard):
        intel= Intelligence(gBoard)
        return intel.Move()

#I tried to separate AI player from normal
#player bu extending the Player class into AI
#However, I was unsuccessful because I didn't 
#know how to use the super() equivalent of Java
#So I just used the player class for AI as well
'''
AI Player attributes

class AI(Player):
    def __init__(self, playerName):
        Player(playerName)   
    
    def NextMove(self,gBoard):
        intel= Intelligence(gBoard)
        return intel.Move() 
'''

'''
Main game method
'''
class Game:
    '''
    Constructor
    Had to use "" condition because Python lacks overloaded constructors
    '''
    def __init__(self, playerOne, playerTwo):
        self.gBoard = Board("")
        self.totalGamesInSession = 0
        self.gameCount = 0
        
        self.Player1Score = 0
        self.Player2Score = 0
                
        if playerTwo == "":
            self.twoPlayer = False
            self.player1 = Player(playerOne)
            #self.player2 = AI("AI")
            self.player2 = Player("AI")
        else:
            self.twoPlayer = True
            self.player1 = Player(playerOne)
            self.player2 = Player(playerTwo)
            

    '''
    Create a new board
    '''
    def newBoard(self):
        self.gBoard = Board("")
        self.gameCount = self.gameCount + 1
    
    '''
    Set alternative player move and check for wins, losses, draws
    '''
    def Move(self, box):
        #Check whether box is empty
        if self.gBoard.SetMove(box) == False:
            return 3   #If not, return 3
        #Else return game state
        return self.gBoard.checkGameState()       
    
    '''
    Return player name
    '''
    def PlayerName(self, playerNo):
        if playerNo == 1:
            return self.player1.playerName
        return self.player2.playerName 
    
    '''
    Keep track of wins
    '''
    def Win(self, playerNo):
        if playerNo == 1:
            self.Player1Score = self.Player1Score + 1
        else:
            self.Player2Score = self.Player2Score + 1
    
    '''
    Return number of wins for a player
    '''
    def Score(self, playerNo):
        if playerNo == 1:
            return self.Player1Score
        else:
            return self.Player2Score
        
    '''
    Reset game
    '''
    def Reset(self):
        self.Player1Score=0
        self.Player2Score=0
        self.gameCount=0
    
    '''
    End game once no. of sessions has expired
    '''
    def endGame(self):
        #If total games desired in current session have been played
        if self.totalGamesInSession == self.gameCount:
            return True     #End game
        #Else
        return False        #Continue
        
   
'''
This is like the main() equivalent of Java/C++
'''

#Read player 1 name
playerOneName = raw_input("Enter Player 1 Name: ")

#Prompt game choice
print ("1) One PLayer Game")
print ("2) Two PLayer Game")
gameChoice = raw_input("Select 1 or 2: ")

#If 2 player game, read player 2 name
if gameChoice == "2":
    playerTwoName = raw_input("Enter Player 2 Name: ")
    #Start 2 player game
    game = Game(playerOneName,playerTwoName)
else:
    #Start 1 player game
    game = Game(playerOneName, "")

choice = "1"
while(choice == "1"): 
    #Number of games desired this session
    numGames = int(input("Number of games you want to play:"))
    game.totalGamesInSession = numGames
    
    endgame = False
    
    
    while endgame == False:
        #Start new board and set player numbers
        game.newBoard()
        pNo = 0                
        turn = True
        while True:
            if pNo == 1:
                pNo = 2
            else:
                pNo = 1
            
            game.gBoard.Print()
            print ''
            print game.PlayerName(pNo), "'s Move: "
            
            #If game is 1 player
            if game.twoPlayer == False and pNo == 2:
                #Create new AI player and assign it to player 2
                a = game.player2
                
                #Calculate AI move
                aiMove = a.NextMove(game.gBoard)
                print (aiMove)
                
                #Make AI move
                gameState = game.Move(aiMove)
            else:
                #Make player move
                gameState = game.Move(int(input()))

            #If move has already been made
            print ''
            if gameState == 3:
                print ("Illegal Move, Please Try Again.")
                if pNo == 1:
                    pNo = 2
                else:
                    pNo = 1
        
            #If legit move
            elif gameState != 0:
                #Print board
                game.gBoard.Print()
                print ''
                
                #If either player wins
                if gameState == 1 or gameState == 2:
                    #Print winner
                    print game.PlayerName(pNo), " Wins!"
                    #Increment win for that player
                    game.Win(pNo)
                
                #Game drawn
                if gameState == -1:
                    print ("The game ended in a draw.")
                break
        
        endgame = game.endGame()
       

    print game.PlayerName(1), " Wins: ", game.Score(1)
    print game.PlayerName(2), " Wins: ", game.Score(2)
    print "Total Games in this Session : ", game.totalGamesInSession
    
    #Reset scores
    game.Reset()
    print ''
    print ("Want to play more?")
    choice = raw_input("1 to continue or any other key to end: ")

