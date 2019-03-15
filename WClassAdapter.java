/*
 *  
 * 
 *  
 *  org.objectweb.asm.ClassAdapter
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.MethodVisitor
 */
package weaver.aop;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import weaver.aop.WAdviceAdapter;

public class WClassAdapter
extends ClassAdapter {
    private String[] methods;
    private String className;
    private String targetClassName;

    public WClassAdapter(ClassVisitor classVisitor, String string, String string2, String[] arrstring) {
        super(classVisitor);
        this.methods = arrstring;
        this.className = string;
        this.targetClassName = string2;
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] arrstring) {
        MethodVisitor methodVisitor = this.cv.visitMethod(n, string, string2, string3, arrstring);
        if (methodVisitor == null || (n & 1280) > 0) {
            return methodVisitor;
        }
        for (int i = 2; i < this.methods.length; ++i) {
            String[] arrstring2 = this.methods[i].split("=");
            if (!(string + string2).equals(arrstring2[0])) continue;
            return new WAdviceAdapter(methodVisitor, n, this.className, this.targetClassName, string, arrstring2[1], string2, string3, arrstring);
        }
        return methodVisitor;
    }
}