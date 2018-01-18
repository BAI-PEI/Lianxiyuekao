package bwie.com.lianxiyuekao.bean;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class DuiXinag {
    private int price;
    private int count;

    public DuiXinag(int price, int count) {
        this.price = price;
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
