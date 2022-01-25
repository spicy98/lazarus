package spicy.lazarus.util.client;

public class LazarusKey {
    int key = -1;
    int scancode = -1;

    public LazarusKey(int key, int scancode) {
        this.key = key;
        this.scancode = scancode;
    }

    public LazarusKey() {}

    public void setCode(int key, int scancode) {
        this.key = key;
        this.scancode = scancode;
    }

    public int[] getCode() {
        return new int[]{key, scancode};
    }


}
