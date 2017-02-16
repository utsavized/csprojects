###############################################
'''
Interpreter/Arithmetic Expression Evaluator
Created on Jan 31, 2011
@author: Utsav Pandey
'''
###############################################

'''
Imports
'''

import string
import math


'''
Read in expression
@return infix string
'''

def read():
    infix = raw_input(">>>")
    return infix

'''
Converts stack elements to expression
@param stack: String
@return: expression (String)
''' 
def toString(stack):
    expression = ""
    length = 0
    while length < len(stack):
        expression = expression + stack[length]
        length = length + 1
    return expression


'''
Calculate precedence between
operators
@param A,B: infix String elements  
@return:
  True if A has higher precedence
  False otherwise
No need to check for braces #they are checked later
'''
def precedence(A,B):
    if A == "!" and B == "!":
        return False
    elif A == "!":
        return True
    elif B == "!":
        return False
    if A == "^" and B == "^":
        return False
    elif A == "^":
        return True
    elif B == "^":
        return False
    elif A == "*" or A == "/" or A == "!":
        return True
    elif B == "*" or B == "/" or B == "!":
        return False
    else:
        return True
    
    
'''
Check if string is operator
@param value: infix string element 
@return: True is operator, False othersise
'''
def isOperator(value):
    if(value == "+" or value == "-" or value == "/" or value == "*" or value == "^" or value == "!"):
        return True
    else:
        return False


'''
Convert from infix expression to
postfix expression
@param infix: infix string
@return: postfix stack (list) 
'''
def infixToPostfix(infix):
    stack = []                  #The operator stack
    postfix = []                #The operand (postfix) stack
    checkPostfix = ""           #Keep track of last postfix element pushed
    checkOperator = False       #Keep track of last operator pushed
    doNothing = ""              #Just a spacer for formatting
    print ''
    
    #for each element in infix expression
    for i in infix:
        
        #if blank space is read in
        #Ignore it
        if i == " ":
            doNothing = ""
        
        #if element is a digit
        #we need to check whether is single digit or
        #a number greater than 9
            #if both stack and postfix are empty 
                #then add the infix element to postfix
            #if either the stack or the postfix are not empty
                #then check if any new operator has been pushed since
                    #if operator has been pushed add infix element as a new number
                #else append that digit to the last pushed digit
       
        elif i in string.digits:
            if len(stack)==0 and len(postfix)==0:
                postfix.append(i)
                print i, ' pushed to Postfix Stack.'
            elif len(stack)!=0 or len(postfix)!=0:
                if(checkOperator):
                    postfix.append(i)
                    print i, ' pushed to Postfix Stack.'
                    checkOperator = False
                else:
                    postfix[len(postfix)-1] = postfix[len(postfix)-1] + i
    
        #if element is an operator
            #and if operator stack is NOT empty
                #get the top element of the stack
                #Make some checks to allow unary operations
                #Until stack is empty and top element of the stack is NOT left brace
                    #check if top stack element has higher precedence than current infix element
                        #if true then pop top stack element and add to postfix until while
                        #condition is met
                    #if precedence is false then no need to continue
                    #in other words, nothing to do. :) so break.
                #For all those operators that don't qualify for the while condition
                    #simply push them to the operator stack to be popped later
            #If element is operator but the operator stack is empty
                #push operator to the stack
            #Else add element to stack
        #if infix element is a left brace
            #then push it to the stack
            #and then do some checks for the unary operations
        #if infix element is a right brace
            #until a left brace is encountered
                #pop the stack and add them elements postfix
            #Finally pop the left brace off the stack.
            #No need to add it to the postfix
        #if infix expression is not a space, or digit or operator or a brace
            #prompt to go back and check where you messed up!
        
                        
        elif isOperator(i):
            if len(stack)!=0:
                topOperator = stack[len(stack)-1]
                
                #This is where we do checks to make sure
                #unary calcs work. We simply push a 0 after a left brace
                #Eg. 4+(-3) = 1. However, it takes a lot more code to calculate that
                #without the help of a zero. Adding the 0 simply makes the exp.
                #4+(-3) => 4+(0-3) = 1 [Same result]
                
                if len(postfix)!=0 and topOperator == "(" and postfix[len(postfix)-1] == checkPostfix:
                        postfix.append("0")
                        print '0 pushed to Postfix Stack.'
                elif len(postfix)==0 and topOperator == "(":
                        postfix.append("0")
                        print '0 pushed to Postfix Stack.'
                while len(stack)!=0 and topOperator!="(":
                    if precedence(topOperator, i):
                        topOperator = stack.pop()
                        postfix.append(topOperator)
                        print topOperator,' pushed to Postfix Stack.' 
                        
                        #this check is required due to the lack of stack.top()
                        #function that is available is Java or C++
                        #this check avoids any possible out of range errors
                        #due to incorrect stack indexing
                        
                        if len(stack)!=0:
                            topOperator = stack[len(stack)-1]
                    else:
                        break
                stack.append(i)
                print i, ' pushed to Operator Stack.'
                checkOperator = True
            
            else:
                
                #yet another check to make the unary calc work
                if len(postfix)==0:
                    postfix.append("0")
                    print '0 pushed to Postfix Stack.'
                
                stack.append(i)
                print i, ' pushed to Operator Stack.'
                checkOperator = True
                
        #checkPostFix used to keep track of last element
        #enetered to the postfix string. This will help
        #solve unary problems -- checkPostfix used
        #if infix element is operator - so refer to that section
        
        elif i == "(":
            stack.append(i)
            checkOperator = True
            if len(postfix)!=0:
                checkPostfix = postfix[len(postfix)-1]
            print '( pushed to Operator Stack.'
        
       
        #Since left brace is always equal to right brace
        #in a valid expression, this maintains
        #brace integrity
        
        elif i == ")":
            topOperator = stack[len(stack)-1]
            while len(stack)!=0 and topOperator!="(":
                topOperator = stack.pop()
                postfix.append(topOperator)
                print topOperator, ' pushed to Postfix Stack.'
                
                #Check required due to lack of stack.pop()
                #Refer to earlier comment for details
                
                if len(stack)!=0:
                    topOperator = stack[len(stack)-1]
            if len(stack)!=0:
                stack.pop()
                print '( popped from the Operator Stack.'
        else:
            print("Don't give me that nonsense!")
           
    #After all said and done, outside the for loop
    #if any more operators left on the stack, pop them
    #and add them all to postfix
    
    while len(stack)!=0:
        topOperator = stack.pop()
        postfix.append(topOperator)
        print topOperator, ' pushed to Postfix Stack.' 
    
    #Print and return the final postfix expression
    print doNothing
    print 'Final Postfix Expression: ', toString(postfix)
    print doNothing
    return postfix


'''
Performs arithmetic operations based on the operator
@param a,b,o: Operands a,b (float) and Operator o (string)
@return: result (float)
'''
def operate(a,b,o):
    if o == "+":
        return b + a
    elif o == "-":
        return b - a
    elif o == "*":
        return b * a
    elif o == "/":
        return b / a
    elif o == "^":
        return math.pow(b,a)
    else:
        return -1


'''
Calculates Factorial
@param val: float
@return: factorial (float) 
'''
def factorial(val):
    if val <= 0:
        fac = 1
    else: 
        fac = val * factorial(val - 1)
    return fac



'''
Calculates the result of
the postfix expression
@param postfix: Stack (String)
@return: result (float)
'''
def calculatePostfix(postfix):
    tempA = ""
    tempB = ""
    stack = []
    
    #If any incorrect postfix expression was entered
    #this is where the Index out of bounds errors show up
    #using try except to catch any exceptions
    #And prevent run from crashing
    try:
        #for all elements in postfix expression
        for i in postfix:
            #if element is a number
            if not(isOperator(i)):
                #push it to the element stack
                stack.append(i)
            #if element is an operator (there is guaranteed 2 elements in the stack)
            #pop top element into A
            #pop the element after that into B
            #Perform B (operator) A
            #push the result back to the stack
            elif isOperator(i):
                if i == "!":
                    tempA = stack.pop()
                    stack.append(factorial(float(tempA)))
                else:    
                    tempA = stack.pop()
                    tempB = stack.pop()
                    stack.append(operate(float(tempA),float(tempB),i))
            
            #if postfix element is neither a digit not an operator
            #then co back and check where you went wrong
            else:
                print ("Don't give me that nonsense!")
        
        #at the end, the only remaining element in the stack 
        #should be the final result
        # so return it!
        return stack.pop()
    
    #catch all index errors
    except IndexError:
        print 'Not a valid postfix expression. Try again'



'''
Gets infix expression from variables (and digits)
@param exp, map: infix expression, map consisting of variable values
@return: infix expression to be converted 
'''
def getInfixValues(exp, map):
    infix = ""
    #read expression
    for i in exp:
        #if expression has a letter
            #try to read the value from the map
        if i in string.letters:
            #if present in map append value to infix string
            if i in map:
                infix = infix + map[i]
            #if not present in map
                #prompt user to input value for the given variable
                #then append the value to infix string
            else:
                print 'Value of ',i, ' not found. Please enter: '
                temp = raw_input('>>>')
                map[i] = temp
                infix = infix + temp
        
        #if expression is not a letter (then must be number or operator)
            #so simply add to infix string
        elif not(i in string.letters):
            infix = infix + i
    
    #return the final infix strong to be converted to postfix
    return infix  



'''
Checks whether input is expression or assignment
@param exp, map: string input, map to store variable - value pairs
@return: map of variable - value pairs 
'''
def checkInfix(exp, map):
    isAssignment = False
    doNothing = ""
    key = ""
    value = ""
    #read string
    for i in exp:
        #if string has =, it is an assignment statement
        if i == "=":
            isAssignment = True
    #if string is assignment statement
        #put variable and value is a map as key-value pairs
    if isAssignment:
        for i in exp:
            if i == " " or i == "=":
                doNothing = ""
            elif i in string.letters:
                key = key + i;
            elif not(i in string.letters):
                value = value + i;
        
        #catch any incorrect assignment statements
        try:
            map[key] = value
            print key, ' = ', value, ' assignment successful.'
            return map
        except UnboundLocalError:
            print 'Invalid assignment statement. Please try again.'
            map["ex"]="Exit"
            print doNothing
            return map
        
    #if not assignment statement
        #then convert infix to postfix
    else:
        #Get numerical values from map into infix string
        infix = getInfixValues(exp, map)
        #convert to postfix        
        postfix = infixToPostfix(infix)
        #compute postfix to get result
        result = calculatePostfix(postfix)
        #print out results
        print 'Result:'
        print infix, ' = ', result
        print ''
        #as a formality
        return map
        


'''
Sort of the "Main()" area
'''

print 'Type exit to end program...'
infix  = ""
map = {"ex":"Safe"}
doNothing = ""

#Keep running until exit is entered
while infix!="exit" or map["ex"] == "Exit":
    #read infix
        #if exit 
            #quit
        #else 
            #check type of input (expression or assignment)
    infix = read()
    if infix == "exit":
        doNothing = ""
    else:
        map = checkInfix(infix, map)
        #if any exceptions, checkInfix will return {ex = Exit}