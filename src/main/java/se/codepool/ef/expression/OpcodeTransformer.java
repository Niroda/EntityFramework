package se.codepool.ef.expression;

import java.util.ArrayList;
import java.util.List;

enum OpcodeTransformer {
	/**
	 * load onto the stack a reference from an array
	 */
	AALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store into a reference in an array
	 */
	AASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push a null reference onto the stack
	 */
	ACONST_NULL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a reference onto the stack from a local variable #index
	 */
	ALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * create a new array of references of length count and component type
	 * identified by the class reference index (indexbyte1 << 8 + indexbyte2) in the
	 * constant pool
	 */
	ANEWARRAY {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * return a reference from a method
	 */
	ARETURN {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * get the length of an array
	 */
	ARRAYLENGTH {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a reference into a local variable #index
	 */
	ASTORE {
		@Override
		String correspondingCode(String latestLine) {
			System.out.println("OPS! " + latestLine);
			if (latestLine != null) {
				if (latestLine.startsWith("LDC \"")) {
					return "String var = " + latestLine.split(" ")[1] + ";";
				}
			}
			return "";
		}
	},
	/**
	 * throws an error or exception (notice that the rest of the stack is cleared,
	 * leaving only a reference to the Throwable)
	 */
	ATHROW {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a byte or Boolean value from an array
	 */
	BALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a byte or Boolean value into an array
	 */
	BASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push a byte onto the stack as an integer value
	 */
	BIPUSH {
		@Override
		String correspondingCode(String latestLine) {
			String value = this.line.split(" ")[1];
			return value;
		}
	},
	/**
	 * reserved for breakpoints in Java debuggers; should not appear in any class
	 * file
	 */
	BREAKPOINT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a char from an array
	 */
	CALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a char into an array
	 */
	CASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * input: indexbyte1, indexbyte2 values: objectref → objectref checks whether an
	 * objectref is of a certain type, the class reference of which is in the
	 * constant pool at index (indexbyte1 << 8 + indexbyte2)
	 */
	CHECKCAST {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a double to a float
	 */
	D2F {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a double to an int
	 */
	D2I {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a double to a long
	 */
	D2L {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * add two doubles
	 */
	DADD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a double from an array
	 */
	DALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a double into an array
	 */
	DASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * compare two doubles
	 */
	DCMPG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * compare two doubles
	 */
	DCMPL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push the constant (a double) onto the stack
	 */
	DCONST {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * divide two doubles
	 */
	DDIV {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a double value from a local variable #index
	 */
	DLOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * multiply two doubles
	 */
	DMUL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * negate a double
	 */
	DNEG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * get the remainder from a division between two doubles
	 */
	DREM {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * return a double from a method
	 */
	DRETURN {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a double value into a local variable #index
	 */
	DSTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * subtract a double from another
	 */
	DSUB {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * duplicate the value on top of the stack
	 */
	DUP {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a float to a double
	 */
	F2D {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a float to an int
	 */
	F2I {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a float to a long
	 */
	F2L {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * add two floats
	 */
	FADD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a float from an array
	 */
	FALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a float in an array
	 */
	FASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * compare two floats
	 */
	FCMPG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * compare two floats
	 */
	FCMPL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * divide two floats
	 */
	FDIV {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a float value from a local variable #index
	 */
	FLOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * multiply two floats
	 */
	FMUL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * negate a float
	 */
	FNEG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * get the remainder from a division between two floats
	 */
	FREM {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * return a float
	 */
	FRETURN {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a float value into a local variable #index
	 */
	FSTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * subtract two floats
	 */
	FSUB {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * get a field value of an object objectref, where the field is identified by
	 * field reference in the constant pool index (indexbyte1 << 8 + indexbyte2)
	 */
	GETFIELD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * get a static field value of a class, where the field is identified by field
	 * reference in the constant pool index (indexbyte1 << 8 + indexbyte2)
	 */
	GETSTATIC {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * goes to another instruction at branchoffset (signed int constructed from
	 * unsigned bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 +
	 * branchbyte4)
	 */
	GOTO_W {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a byte
	 */
	I2B {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a character
	 */
	I2C {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a double
	 */
	I2D {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a float
	 */
	I2F {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a long
	 */
	I2L {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert an int into a short
	 */
	I2S {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * add two ints
	 */
	IADD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load an int from an array
	 */
	IALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * perform a bitwise AND on two integers
	 */
	IAND {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store an int into an array
	 */
	IASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 0 onto the stack
	 */
	ICONST_0 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 1 onto the stack
	 */
	ICONST_1 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 2 onto the stack
	 */
	ICONST_2 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 3 onto the stack
	 */
	ICONST_3 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 4 onto the stack
	 */
	ICONST_4 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value 5 onto the stack
	 */
	ICONST_5 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load the int value −1 onto the stack
	 */
	ICONST_M1 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * divide two integers
	 */
	IDIV {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if references are equal, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ACMPEQ {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if references are not equal, branch to instruction at branchoffset (signed
	 * short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ACMPNE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if ints are equal, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ICMPEQ {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value1 is greater than or equal to value2, branch to instruction at
	 * branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 +
	 * branchbyte2)
	 */
	IF_ICMPGE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value1 is greater than value2, branch to instruction at branchoffset
	 * (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ICMPGT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value1 is less than or equal to value2, branch to instruction at
	 * branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 +
	 * branchbyte2)
	 */
	IF_ICMPLE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value1 is less than value2, branch to instruction at branchoffset (signed
	 * short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ICMPLT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if ints are not equal, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IF_ICMPNE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is 0, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFEQ {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is greater than or equal to 0, branch to instruction at branchoffset
	 * (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFGE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is greater than 0, branch to instruction at branchoffset (signed
	 * short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFGT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is less than or equal to 0, branch to instruction at branchoffset
	 * (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFLE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is less than 0, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFLT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is not 0, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFNE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is not null, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFNONNULL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * if value is null, branch to instruction at branchoffset (signed short
	 * constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	 */
	IFNULL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * increment local variable #index by signed byte const
	 */
	IINC {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load an int value from a local variable #index
	 */
	ILOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * reserved for implementation-dependent operations within debuggers; should not
	 * appear in any class file
	 */
	IMPDEP1 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * multiply two integers
	 */
	IMUL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * negate int
	 */
	INEG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * invokes a dynamic method and puts the result on the stack (might be void);
	 * the method is identified by method reference index in constant pool
	 * (indexbyte1 << 8 + indexbyte2)
	 */
	INVOKEDYNAMIC {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * invokes an interface method on object objectref and puts the result on the
	 * stack (might be void); the interface method is identified by method reference
	 * index in constant pool (indexbyte1 << 8 + indexbyte2)
	 */
	INVOKEINTERFACE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * invoke instance method on object objectref and puts the result on the stack
	 * (might be void); the method is identified by method reference index in
	 * constant pool (indexbyte1 << 8 + indexbyte2)
	 */
	INVOKESPECIAL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * invoke a static method and puts the result on the stack (might be void); the
	 * method is identified by method reference index in constant pool (indexbyte1
	 * << 8 + indexbyte2)
	 */
	INVOKESTATIC {
		@Override
		String correspondingCode(String latestLine) {
			if(latestLine != null && latestLine.startsWith("INVOKESTATIC")) {
			String[] arr = latestLine.split(" ");
			String[] typePath = arr[2].split("/");
			String[] invokedFunc = arr[1].split("/");
			System.out.println("WELL !!! " + latestLine);
			return typePath[typePath.length - 1] + " var = "
					+ invokedFunc[invokedFunc.length - 1];
			}
			return null;
		}
	},
	/**
	 * invoke virtual method on object objectref and puts the result on the stack
	 * (might be void); the method is identified by method reference index in
	 * constant pool (indexbyte1 << 8 + indexbyte2)
	 */
	INVOKEVIRTUAL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise int OR
	 */
	IOR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * logical int remainder
	 */
	IREM {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * return an integer from a method
	 */
	IRETURN {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * int shift left
	 */
	ISHL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * int arithmetic shift right
	 */
	ISHR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store int value into variable #index
	 */
	ISTORE {
		@Override
		String correspondingCode(String latestLine) {
			return "int var  = " + latestLine;
		}
	},
	/**
	 * int subtract
	 */
	ISUB {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * int logical shift right
	 */
	IUSHR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * int xor
	 */
	IXOR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * jump to subroutine at branchoffset (signed short constructed from unsigned
	 * bytes branchbyte1 << 8 + branchbyte2) and place the return address on the
	 * stack
	 */
	JSR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * jump to subroutine at branchoffset (signed int constructed from unsigned
	 * bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4)
	 * and place the return address on the stack
	 */
	JSR_W {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a long to a double
	 */
	L2D {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a long to a float
	 */
	L2F {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * convert a long to a int
	 */
	L2I {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * add two longs
	 */
	LADD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a long from an array
	 */
	LALOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise AND of two longs
	 */
	LAND {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a long to an array
	 */
	LASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push 0 if the two longs are the same, 1 if value1 is greater than value2, -1
	 * otherwise
	 */
	LCMP {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push (the number zero with type long) onto the stack
	 */
	LCONST {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push a constant #index from a constant pool (String, int, float, Class,
	 * java.lang.invoke.MethodType, or java.lang.invoke.MethodHandle) onto the stack
	 */
	LDC {
		@Override
		String correspondingCode(String latestLine) {
			return this.line;
		}
	},
	/**
	 * divide two longs
	 */
	LDIV {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load a long value from a local variable #index
	 */
	LLOAD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * multiply two longs
	 */
	LMUL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * negate a long
	 */
	LNEG {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * a target address is looked up from a table using a key and execution
	 * continues from the instruction at that address
	 */
	LOOKUPSWITCH {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise OR of two longs
	 */
	LOR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * remainder of division of two longs
	 */
	LREM {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * return a long value
	 */
	LRETURN {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise shift left of a long value1 by int value2 positions
	 */
	LSHL {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise shift right of a long value1 by int value2 positions
	 */
	LSHR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store a long value in a local variable #index
	 */
	LSTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * subtract two longs
	 */
	LSUB {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise shift right of a long value1 by int value2 positions, unsigned
	 */
	LUSHR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * bitwise XOR of two longs
	 */
	LXOR {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * enter monitor for object ("grab the lock" – start of synchronized() section)
	 */
	MONITORENTER {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * exit monitor for object ("release the lock" – end of synchronized() section)
	 */
	MONITOREXIT {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * create a new array of dimensions dimensions of type identified by class
	 * reference in constant pool index (indexbyte1 << 8 + indexbyte2); the sizes of
	 * each dimension is identified by count1, [count2, etc.]
	 */
	MULTIANEWARRAY {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * create new array with count elements of primitive type identified by atype
	 */
	NEWARRAY {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * perform no operation
	 */
	NOP {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * discard the top value on the stack
	 */
	POP {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * discard the top two values on the stack (or one value, if it is a double or
	 * long)
	 */
	POP2 {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * set field to value in an object objectref, where the field is identified by a
	 * field reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	 */
	PUTFIELD {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * set static field to value in a class, where the field is identified by a
	 * field reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	 */
	PUTSTATIC {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * continue execution from address taken from a local variable #index (the
	 * asymmetry with jsr is intentional)
	 */
	RET {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * load short from array
	 */
	SALOAD
	{
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * store short to array
	 */
	SASTORE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * push a short onto the stack as an integer value
	 */
	SIPUSH {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * swaps two top words on the stack (note that value1 and value2 must not be
	 * double or long)
	 */
	SWAP {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * continue execution from an address in the table at offset index
	 */
	TABLESWITCH {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	/**
	 * execute opcode, where opcode is either iload, fload, aload, lload, dload,
	 * istore, fstore, astore, lstore, dstore, or ret, but assume the index is 16
	 * bit; or execute iinc, where the index is 16 bits and the constant to
	 * increment by is a signed 16 bit short
	 */
	WIDE {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	},
	
	GOTO {
		@Override
		String correspondingCode(String latestLine) {
			return null;
		}
	};
	
	String line;

	/**
	 * new, return, instanceof, goto to be checked
	 * 
	 * @param opcodeLine
	 */
	static OpcodeTransformer parseOpcode(String opcodeLine) {
		try {
			String[] parts = opcodeLine.split(" ");
			OpcodeTransformer transformed = OpcodeTransformer.valueOf(parts[0]);
			transformed.line = opcodeLine;
			return transformed;
		} catch (Exception e) {
			return OpcodeTransformer.AALOAD;
			//throw new RuntimeException(e.getMessage());
		}
	}

	String correspondingCode(String latestLine) {
		return this.line;
	}
}
