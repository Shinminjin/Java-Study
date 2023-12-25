package study.javacoding.chapter02.problem053.deep.copy.serialization;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Radius implements Serializable {

    @Serial
    private static final long serialVersionUID = -7706559257521522378L;

    private int start;
    private int end;
}
