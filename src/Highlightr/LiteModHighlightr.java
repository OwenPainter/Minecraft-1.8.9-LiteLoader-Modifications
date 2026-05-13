import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

@ExposableOptions(strategy=ConfigStrategy.Unversioned, filename="highlightr.json")
public class LiteModHighlightr
implements LiteMod,
PostRenderListener,
Tickable,
Configurable {
    public static LiteModHighlightr instance;
    @Expose
    @SerializedName(value="enabled")
    public boolean enabled = true;
    @Expose
    @SerializedName(value="target_block_id")
    public String targetBlockId = "minecraft:diamond_ore";
    @Expose
    @SerializedName(value="scan_radius")
    public int scanRadius = 32;
    private List<cj> foundBlocks = new ArrayList<cj>();
    private int tickCounter = 0;

    public LiteModHighlightr() {
        instance = this;
    }

    public String getName() {
        return "Highlightr";
    }

    public String getVersion() {
        return "1.0.0";
    }

    public void init(File file) {
    }

    public void upgradeSettings(String string, File file, File file2) {
    }

    public Class<? extends ConfigPanel> getConfigPanelClass() {
        return HighlightrConfigPanel.class;
    }

    public void onTick(ave ave2, float f, boolean bl, boolean bl2) {
        if (bl && bl2) {
            if (!this.enabled) {
                if (!this.foundBlocks.isEmpty()) {
                    this.foundBlocks.clear();
                }
                return;
            }
            ++this.tickCounter;
            if (this.tickCounter % 20 == 0) {
                this.scanBlocks(ave2);
            }
        }
    }

    private void scanBlocks(ave ave2) {
        int n;
        if (ave2.f == null || ave2.h == null) {
            return;
        }
        ArrayList<cj> arrayList = new ArrayList<cj>();
        pk pk2 = ave2.ac();
        if (pk2 == null) {
            pk2 = ave2.h;
        }
        if (pk2 == null) {
            return;
        }
        int n2 = (int)pk2.s;
        int n3 = (int)pk2.t;
        int n4 = (int)pk2.u;
        afh afh2 = afh.b((String)this.targetBlockId);
        if (afh2 == null) {
            try {
                n = Integer.parseInt(this.targetBlockId);
                afh2 = afh.c((int)n);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (afh2 == null) {
            this.foundBlocks = arrayList;
            return;
        }
        n = this.scanRadius;
        for (int i = n2 - n; i <= n2 + n; ++i) {
            for (int j = Math.max(0, n3 - n); j <= Math.min(255, n3 + n); ++j) {
                for (int k = n4 - n; k <= n4 + n; ++k) {
                    cj cj2 = new cj(i, j, k);
                    if (ave2.f.p(cj2).c() != afh2) continue;
                    arrayList.add(cj2);
                }
            }
        }
        this.foundBlocks = arrayList;
    }

    public void onPostRenderEntities(float f) {
    }

    public void onPostRender(float f) {
        if (!this.enabled || this.foundBlocks == null || this.foundBlocks.isEmpty()) {
            return;
        }
        ave ave2 = ave.A();
        biu biu2 = ave2.af();
        double d = biu2.h;
        double d2 = biu2.i;
        double d3 = biu2.j;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glTranslated((double)(-d), (double)(-d2), (double)(-d3));
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.0f, (float)0.4f);
        for (cj cj2 : this.foundBlocks) {
            this.drawBox(cj2.n(), cj2.o(), cj2.p());
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void drawBox(double d, double d2, double d3) {
        double d4 = 1.002;
        double d5 = (1.0 - d4) / 2.0;
        bfx bfx2 = bfx.a();
        bfd bfd2 = bfx2.c();
        bfd2.a(7, bms.e);
        bfd2.b(d + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d5, d2 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d5, d2 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d4 + d5, d3 + d4 + d5).d();
        bfd2.b(d + d4 + d5, d2 + d5, d3 + d4 + d5).d();
        bfx2.b();
    }
}

