package net.easymfne.settingsapi;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class SettingsAPITransformer
implements IClassTransformer {
    public byte[] transform(String string, String string2, byte[] byArray) {
        if ("bfk".equals(string) || "net.minecraft.client.renderer.EntityRenderer".equals(string2)) {
            try {
                return this.patchEntityRenderer(byArray);
            }
            catch (Exception exception) {
                return byArray;
            }
        }
        return byArray;
    }

    private byte[] patchEntityRenderer(byte[] byArray) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(byArray);
        classReader.accept((ClassVisitor)classNode, 0);
        for (Object e : classNode.methods) {
            MethodInsnNode methodInsnNode;
            MethodNode methodNode = (MethodNode)e;
            if (!"(F)V".equals(methodNode.desc)) continue;
            boolean bl = false;
            for (AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray()) {
                if (abstractInsnNode.getOpcode() != 182) continue;
                methodInsnNode = (MethodInsnNode)abstractInsnNode;
                if (!"bda".equals(methodInsnNode.owner) && !"net/minecraft/client/multiplayer/PlayerControllerMP".equals(methodInsnNode.owner) || !"d".equals(methodInsnNode.name) && !"getBlockReachDistance".equals(methodInsnNode.name) && !"func_78757_d".equals(methodInsnNode.name) || !"()F".equals(methodInsnNode.desc)) continue;
                bl = true;
                break;
            }
            if (!bl && !"a".equals(methodNode.name) && !"getMouseOver".equals(methodNode.name) && !"func_78473_a".equals(methodNode.name)) continue;
            for (AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray()) {
                MethodInsnNode methodInsnNode2;
                if (abstractInsnNode.getOpcode() == 182) {
                    methodInsnNode = (MethodInsnNode)abstractInsnNode;
                    if (("bda".equals(methodInsnNode.owner) || "net/minecraft/client/multiplayer/PlayerControllerMP".equals(methodInsnNode.owner)) && ("d".equals(methodInsnNode.name) || "getBlockReachDistance".equals(methodInsnNode.name) || "func_78757_d".equals(methodInsnNode.name)) && "()F".equals(methodInsnNode.desc)) {
                        methodInsnNode2 = new MethodInsnNode(184, "net/easymfne/settingsapi/LiteModSettingsAPI", "getReach", "(Ljava/lang/Object;)F", false);
                        methodNode.instructions.set(abstractInsnNode, (AbstractInsnNode)methodInsnNode2);
                    }
                }
                if (abstractInsnNode.getOpcode() != 18) continue;
                methodInsnNode = (LdcInsnNode)abstractInsnNode;
                if (!(methodInsnNode.cst instanceof Double) || (Double)methodInsnNode.cst != 3.0) continue;
                methodInsnNode2 = new MethodInsnNode(184, "net/easymfne/settingsapi/LiteModSettingsAPI", "getCombatReach", "()D", false);
                methodNode.instructions.set(abstractInsnNode, (AbstractInsnNode)methodInsnNode2);
            }
        }
        ClassWriter classWriter = new ClassWriter(1);
        classNode.accept((ClassVisitor)classWriter);
        return classWriter.toByteArray();
    }
}

