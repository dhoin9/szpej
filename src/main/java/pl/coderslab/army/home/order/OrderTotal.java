package pl.coderslab.army.home.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderTotal {
    private String name;
    private String size;
    private String warehouse;
    private int count;

    @Override
    public String toString() {
        return "OrderTotal{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", count=" + count +
                '}';
    }
}
