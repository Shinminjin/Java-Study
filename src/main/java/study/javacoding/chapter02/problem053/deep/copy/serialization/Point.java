package study.javacoding.chapter02.problem053.deep.copy.serialization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Point implements Serializable {

    @Serial
    private static final long serialVersionUID = 2592024436429064882L;

    private double x;
    private double y;
    private Radius radius;
}
