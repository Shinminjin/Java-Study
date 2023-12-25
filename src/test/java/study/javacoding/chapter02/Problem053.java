package study.javacoding.chapter02;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.io.*;

// 객체 복사
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem053 {

    @Test
    void 수동_얕은_복사() {
        log.info("Shallow copy via manual copy");

        study.javacoding.chapter02.problem053.shallow.copy.manually.Point point1
                = new study.javacoding.chapter02.problem053.shallow.copy.manually.Point(5, 5);
        log.info("Point1 (original), (x, y): ({}, {})", point1.getX(), point1.getY());

        study.javacoding.chapter02.problem053.shallow.copy.manually.Point clone1
                = point1.clonePoint();
        point1.setX(10);
        point1.setY(10);

        log.info("Point1 (modified), (x, y): ({}, {})", point1.getX(), point1.getY());
        log.info("Clone1, (x, y): ({}, {})", clone1.getX(), clone1.getY());
    }

    @Test
    void clone_메서드를_통한_얕은_복사() throws CloneNotSupportedException {
        log.info("Shallow copy via the clone() method");

        study.javacoding.chapter02.problem053.shallow.copy.clone.Point point2
                = new study.javacoding.chapter02.problem053.shallow.copy.clone.Point(5, 5);
        log.info("Point2 (original), (x, y): ({}, {})", point2.getX(), point2.getY());

        study.javacoding.chapter02.problem053.shallow.copy.clone.Point clone2
                = point2.clone();
        point2.setX(10);
        point2.setY(10);

        log.info("Point2 (modified), (x, y): ({}, {})", point2.getX(), point2.getY());
        log.info("Clone2, (x, y): ({}, {})", clone2.getX(), clone2.getY());
    }

    @Test
    void 생성자를_통한_얕은_복사() {
        log.info("Shallow copy via constructor");
        study.javacoding.chapter02.problem053.shallow.copy.constructor.Point point3
                = new study.javacoding.chapter02.problem053.shallow.copy.constructor.Point(5, 5);
        log.info("Point3 (original), (x, y): ({}, {})", point3.getX(), point3.getY());

        study.javacoding.chapter02.problem053.shallow.copy.constructor.Point clone3
                = new study.javacoding.chapter02.problem053.shallow.copy.constructor.Point(point3);
        point3.setX(10);
        point3.setY(10);

        log.info("Point3 (modified), (x, y): ({}, {})", point3.getX(), point3.getY());
        log.info("Clone3, (x, y): ({}, {})", clone3.getX(), clone3.getY());
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
