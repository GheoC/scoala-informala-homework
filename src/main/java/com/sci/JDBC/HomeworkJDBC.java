package com.sci.JDBC;

public class HomeworkJDBC {


    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Operations_JDBC operations_jdbc = new Operations_JDBC();

        System.out.println("****************************************************");
        System.out.println("Display data for all classes: ");
        operations_jdbc.queryAllClasses();

        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("Display data for a course with specified id: ");

        operations_jdbc.queryByCourseID(5);
        System.out.println("*****************************************************");
        operations_jdbc.queryByCourseID(2);
        System.out.println("*****************************************************");
        operations_jdbc.queryByCourseID(45);

        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("Display data for all classes whose title (when converted to all lowercase letters)" +
                " begins with \"intro\".");
        operations_jdbc.queryByCourseName("intro");

        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("Display data for all classes whose dept (when converted to all lowercase letters) " +
                "is \"cos\" and whose coursenum begins with \"3\".");
        operations_jdbc.queryByDept("COS");

        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("Display data for all classes whose dept (when converted to all lowercase letters) " +
                "is \"science\" and whose coursenum begins with \"3\".");
        operations_jdbc.queryByDept("scIENce");


        operations_jdbc.closeConnection();
    }
}
