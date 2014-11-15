#Quantum VM

This program was designed with the intent of simulating a Quantum Computer. It is based on my comprehension of material about quantum mechanics and quantum computing, which may or may not be accurate, to be honest.

NOTE: This is not an actual quantum computer! It is merely an attempt to SIMULATE a quantum computer on a classic computer under the limitations of binary computing.

##About the Program

The program uses several classes to hold data used to run the simulation. The QuantumComputer class holds a QuantumRegister, which holds an array of Qubits. The Qubit class attempts to mimic real-world Qubits, in that measurement alters their value. Also included in the code is a vvery simple interpreter I wrote to allow very basic code to be written for the "computer" (see SQSL below)

###Quantum Mechanics and Qubits

Qubits are designed so that they act the ways quantum particles do in quantum physics. To put it in my own words, Qubits can have multiple values simultaneously. This is done by representing them in three dimensions with an X, Y, and Z value, where (X-0.5)^2 + (y-0.5)^2 + (z-0.5)^2 = 1. From what I have read, the value can be less than one, but I simply made the equation always equal one to make things simpler. This can obviously be modified in the source code. 

#####Uncertainty Principle and Measurement

The Qubits also obey Heisenberg's Uncertainty Principle, where in the case of quantum computing, the act of measurement can change the value of the Qubit based on probability. This is a problem with quantum computing because it can get rid of a superposition.

#####Quantum Entanglement

The program has a limited support for Quantum Entanglement. Basically, this means that two particles can have correlating values. My assumptions led me to the belief that the two particles will have exactly opposite values, meaning that if X is 0.6 for one particle, the other will measure 0.4.

#####Logic Gates

In order to allow interaction with the qubits, I created code to mimic several quantum logic gates. These allow for operations to be performed on one or more qubits.

######Pauli Gates

Three of these gates exist, an X, Y, and Z gate. These gates simply reverse the value on the dimension of the qubit given as input. For example, a qubit with a Y value equal to 0.9 would be changed so that Y equals 0.1.

######Swap Gates

Swap gates are used to swap two qubits between indexes. For example, if qubits 2 and 3 were put into a Swap gate, 2 would take 3's place, and vice versa.

##SQSL

The Simulated Quantum Scripting Language, also abbreviated as SQSL, or even Squazzle (I came up with that as a way to pronounce SQSL), is a interpreter-based language I created using two classes, one of which is a Main class called ScriptLauncher (the other is a tester, which can be deleted without any problems). This program reads from a filename that the user can specify, or a file named script.txt located in the resources folder.

SQSL only has a few lines of code that can be used. These lines of code allow for the use of the logic gates mentioned above, as well as output for any values provided. Currently, this only includes measurement values.

###Basic Syntax

Several things will help with writing in SQSL. First, one can put as many spaces as they want before the code. However, one should never put any other spaces in the code, as it will almost certainly cause the program to fail. However, blank lines are allowed.

Comments can be added to a program with a pair of slashes (//). Anything with a pair of slashes at the beginning of it will be ignored. Using qubits in a program requires the use of an "at" symbol (@), followed by the index. For example, @3 refers to the qubit at index 3. The use of the @ symbol is intended as a precaution in case classical bits are eventually used in the interpreter.

###Creating a Quantum Register

In any program, one will require a register to hold the values. To do so, one can write something like this:

    #8|{2^3}{6^7}

The first part of this code, the #, indicates that a new register will be created. The size of this register is 8, which is followed by a |. This is used to separate the register size from the list of entangled pairs of qubits, if there are any. In  this case, there are two. Each pair, as shown above, is contained in curly brackets, like this:

    {a^b}

In the example above, the qubit at index a would be entangled to the qubit at index b. They are separated by a caret (^), which symbolizes the "branch", so to speak, that connects them via quantum entanglement. Applying this to the original register declaration, we can tell that index 2 is entangled to index 3, and index 6 is entangled to index 7.

###Logic Gates

At the moment, only two main types of logic gates exist for use. More will eventually be added, but for now, one can experiment with what has already been written.

#####Pauli Gate

The first is the Pauli gate, of which three exist, as demonstrated below:

    //Pauli-X gate
    PauliX(@n)
    
    //Pauli-Y gate
    PauliY(@n)
    
    //Pauli-Z gate
    PauliZ(@n)

In each case, the respective gates are being used on the qubit at index n.

#####Swap Gate

The Swap gate will swap two qubits between their positions. One can see the above description for more information. The syntax as follows:

    Swap(@a,@b)

This will swap the qubits at indexes a and b. The comma is obviously there to separate the two values and avoid confusion.

###Output

Output allows for values to be displayed on the screen. There are two ways to output this.

    Output(param)
    OutputLn(param)

The only difference between the two is that OutputLn will go to a new line after performing its task. The param in the parenteses can be replaced by any compatible values. This does NOT include qubits, since they are a superposition of multiple values.

#####Measurement

Measurement can be done through the use of one of three possible code segments, depending on the dimension. For example, measuring the X-axis of the qubit at index n would be done by writing:

    MeasureX(@n)

For the Y and Z dimensions, replacing X with the proper value will get the desired code. Keep in mind that for measurement, it will guarantee either a 0 or 1 as output. This means that it can be used as a parameter for the Output and OutputLn code segments.

##Future Plans

In the future, I would like to drastically improve the amount of content available in the program. I have been looking into CNOT gates, but have had no luck so far in trying to figure out how to properly simulate them. This program will never truly be quantum, but that is not the goal. Instead, I am trying to simulate it, and give a user a general idea of what one could do with a quantum computer.
