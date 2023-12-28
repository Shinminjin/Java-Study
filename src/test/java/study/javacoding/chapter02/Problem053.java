package study.javacoding.chapter02;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;
import study.javacoding.chapter02.problem053.shallow.Radius;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

// 객체 복사
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem053 {

    @Test
    void 수동_얕은_복사() {
        log.info("Shallow copy via manual copy");

        Radius radius1 = new Radius();
        radius1.setStart(0);
        radius1.setEnd(10);

        study.javacoding.chapter02.problem053.shallow.copy.manually.Point point1
                = new study.javacoding.chapter02.problem053.shallow.copy.manually.Point(5, 5, radius1);
        log.info("Point1 (original), (x, y, radius): ({}, {}, {})", point1.getX(), point1.getY(), point1.getRadius());

        study.javacoding.chapter02.problem053.shallow.copy.manually.Point clone1
                = point1.clonePoint();

        // 복사 시, Radius 의 heap 주소 값이 변하지 않는 것을 확인할 수 있음
        log.info("Point1, (x, y, radius): ({}, {}, {})", point1.getX(), point1.getY(), point1.getRadius()); // Radius@2c5529ab
        log.info("Clone1, (x, y, radius): ({}, {}, {})", clone1.getX(), clone1.getY(), clone1.getRadius()); // Radius@2c5529ab
        assertThat(point1.getRadius().hashCode()).isEqualTo(point1.getRadius().hashCode());

        // point1 수정
        point1.setX(10);
        point1.setY(10);
        point1.getRadius().setStart(5);
        point1.getRadius().setEnd(100);

        // point1 Radius 값 변경 시, clone1 의 Radius 값도 같이 변하는 것을 확인할 수 있음
        log.info("Point1 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point1.getX(), point1.getY(), point1.getRadius().getStart(), point1.getRadius().getEnd());
        log.info("Clone1, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone1.getX(), clone1.getY(), clone1.getRadius().getStart(), clone1.getRadius().getEnd());
    }

    @Test
    void clone_메서드를_통한_얕은_복사() throws CloneNotSupportedException {
        log.info("Shallow copy via the clone() method");

        Radius radius2 = new Radius();
        radius2.setStart(0);
        radius2.setEnd(10);

        study.javacoding.chapter02.problem053.shallow.copy.clone.Point point2
                = new study.javacoding.chapter02.problem053.shallow.copy.clone.Point(5, 5, radius2);
        log.info("Point2 (original), (x, y, radius): ({}, {}, {})", point2.getX(), point2.getY(), point2.getRadius());

        study.javacoding.chapter02.problem053.shallow.copy.clone.Point clone2
                = point2.clone();

        // 복사 시, Radius 의 heap 주소 값이 변하지 않는 것을 확인할 수 있음
        log.info("Point2, (x, y, radius): ({}, {}, {})", point2.getX(), point2.getY(), point2.getRadius()); // Radius@2631f68c
        log.info("Clone2, (x, y, radius): ({}, {}, {})", clone2.getX(), clone2.getY(), clone2.getRadius()); // Radius@2631f68c
        assertThat(point2.getRadius().hashCode()).isEqualTo(point2.getRadius().hashCode());

        // point2 수정
        point2.setX(10);
        point2.setY(10);
        point2.getRadius().setStart(5);
        point2.getRadius().setEnd(100);

        // point2 radius 값 변경 시, clone2 의 radius 값도 같이 변하는 것을 확인할 수 있음
        log.info("Point2 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point2.getX(), point2.getY(), point2.getRadius().getStart(), point2.getRadius().getEnd());
        log.info("Clone2, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone2.getX(), clone2.getY(), clone2.getRadius().getStart(), clone2.getRadius().getEnd());
    }

    @Test
    void 생성자를_통한_얕은_복사() {
        log.info("Shallow copy via constructor");

        Radius radius3 = new Radius();
        radius3.setStart(0);
        radius3.setEnd(10);

        study.javacoding.chapter02.problem053.shallow.copy.constructor.Point point3
                = new study.javacoding.chapter02.problem053.shallow.copy.constructor.Point(5, 5, radius3);
        log.info("Point3 (original), (x, y, radius): ({}, {}, {})", point3.getX(), point3.getY(), radius3);

        study.javacoding.chapter02.problem053.shallow.copy.constructor.Point clone3
                = new study.javacoding.chapter02.problem053.shallow.copy.constructor.Point(point3);

        // 복사 시, Radius 의 heap 주소 값이 변하지 않는 것을 확인할 수 있음
        log.info("Point3, (x, y, radius): ({}, {}, {})", point3.getX(), point3.getY(), point3.getRadius()); // Radius@51972dc7
        log.info("Clone3, (x, y, radius): ({}, {}, {})", clone3.getX(), clone3.getY(), clone3.getRadius()); // Radius@51972dc7
        assertThat(point3.getRadius().hashCode()).isEqualTo(point3.getRadius().hashCode());

        // point3 수정
        point3.setX(10);
        point3.setY(10);
        point3.getRadius().setStart(5);
        point3.getRadius().setEnd(100);

        // point3 radius 값 변경 시, clone3 의 radius 값도 같이 변하는 것을 확인할 수 있음
        log.info("Point3 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point3.getX(), point3.getY(), point3.getRadius().getStart(), point3.getRadius().getEnd());
        log.info("Clone3, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone3.getX(), clone3.getY(), clone3.getRadius().getStart(), clone3.getRadius().getEnd());
    }

    @Test
    void 직렬화를_통한_깊은_복사() {
        study.javacoding.chapter02.problem053.deep.copy.serialization.Radius radius4
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Radius();
        radius4.setStart(0);
        radius4.setEnd(10);

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point point4
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Point(5, 5, radius4);
        log.info("Point4 (original), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point4.getX(), point4.getY(), point4.getRadius().getStart(), point4.getRadius().getEnd());

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point clone4
                = SerializationUtils.clone(point4);

        // 복사 시, Radius 의 heap 주소 값이 변함 -> 깊은 복사 확인
        log.info("Point4, (x, y, radius): ({}, {}, {})", point4.getX(), point4.getY(), point4.getRadius()); // Radius@59cba5a
        log.info("Clone4, (x, y, radius): ({}, {}, {})", clone4.getX(), clone4.getY(), clone4.getRadius()); // Radius@71329995
        assertThat(point4.getRadius().hashCode()).isEqualTo(point4.getRadius().hashCode());

        // point4 수정
        point4.setX(10);
        point4.setY(10);
        point4.getRadius().setStart(5);
        point4.getRadius().setEnd(100);

        log.info("Point4 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point4.getX(), point4.getY(), point4.getRadius().getStart(), point4.getRadius().getEnd());
        log.info("Clone4, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone4.getX(), clone4.getY(), clone4.getRadius().getStart(), clone4.getRadius().getEnd());
    }

    private <T> T cloneThroughSerialization(T t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            return t;
        }
    }

    @Test
    void 직렬화_헬퍼_메서드를_통한_깊은_복사() {
        log.info("Deep copy via serialization helper method");
        study.javacoding.chapter02.problem053.deep.copy.serialization.Radius radius5
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Radius();
        radius5.setStart(0);
        radius5.setEnd(10);

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point point5
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Point(5, 5, radius5);
        log.info("Point5 (original), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point5.getX(), point5.getY(), point5.getRadius().getStart(), point5.getRadius().getEnd());

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point clone5
                = cloneThroughSerialization(point5);

        // 복사 시, Radius 의 heap 주소 값이 변함 -> 깊은 복사 확인
        log.info("Point5, (x, y, radius): ({}, {}, {})", point5.getX(), point5.getY(), point5.getRadius()); // Radius@531f4093
        log.info("Clone5, (x, y, radius): ({}, {}, {})", clone5.getX(), clone5.getY(), clone5.getRadius()); // Radius@62ef27a8
        assertThat(point5.getRadius().hashCode()).isEqualTo(point5.getRadius().hashCode());

        // point5 수정
        point5.setX(10);
        point5.setY(10);
        point5.getRadius().setStart(5);
        point5.getRadius().setEnd(100);

        log.info("Point5 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point5.getX(), point5.getY(), point5.getRadius().getStart(), point5.getRadius().getEnd());
        log.info("Clone5, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone5.getX(), clone5.getY(), clone5.getRadius().getStart(), clone5.getRadius().getEnd());
    }

    @SuppressWarnings("unchecked")
    private <T> T cloneThroughJson(T t) {
        Gson gson = new Gson();
        String json = gson.toJson(t);
        return (T) gson.fromJson(json, t.getClass());
    }

    @Test
    void JSON_을_통한_깊은_복사() {
        log.info("Deep copy via JSON (Gson)");
        study.javacoding.chapter02.problem053.deep.copy.serialization.Radius radius6
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Radius();
        radius6.setStart(0);
        radius6.setEnd(10);

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point point6
                = new study.javacoding.chapter02.problem053.deep.copy.serialization.Point(5, 5, radius6);
        log.info("Point6 (original), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point6.getX(), point6.getY(), point6.getRadius().getStart(), point6.getRadius().getEnd());

        study.javacoding.chapter02.problem053.deep.copy.serialization.Point clone6
                = cloneThroughJson(point6);

        // 복사 시, Radius 의 heap 주소 값이 변함 -> 깊은 복사 확인
        log.info("Point6, (x, y, radius): ({}, {}, {})", point6.getX(), point6.getY(), point6.getRadius()); // Radius@531f4093
        log.info("Clone6, (x, y, radius): ({}, {}, {})", clone6.getX(), clone6.getY(), clone6.getRadius()); // Radius@62ef27a8
        assertThat(point6.getRadius().hashCode()).isEqualTo(point6.getRadius().hashCode());

        // point6 수정
        point6.setX(10);
        point6.setY(10);
        point6.getRadius().setStart(5);
        point6.getRadius().setEnd(100);

        log.info("Point6 (modified), (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                point6.getX(), point6.getY(), point6.getRadius().getStart(), point6.getRadius().getEnd());
        log.info("Clone6, (x, y, Radius start, Radius end): ({}, {}, {}, {})",
                clone6.getX(), clone6.getY(), clone6.getRadius().getStart(), clone6.getRadius().getEnd());
    }
}
