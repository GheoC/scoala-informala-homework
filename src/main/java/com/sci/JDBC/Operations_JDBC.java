package com.sci.JDBC;

import java.sql.*;

/** Connects to the school database consists of these five tables:
 * 1. classes with Fields: classid, courseid, days, starttime, endtime, bldg, roomnum
 * 2. courses with Fields: courseid, area, title, descrip, prereqs
 * 3. crosslistings with Fields: courseid, dept, coursenum
 * 4. coursesprofs with Fields: courseid, profid
 * 5. profs with Fields: profid, profname
 *
 *  This class implements the following methods to:
 *
 * - Display data for all classes.
 * - Display data for a course with specified id.
 * - Display data for all classes whose title (when converted to all lowercase letters) begins with "intro".
 * - Display data for all classes whose dept (when converted to all lowercase letters) is "cos" and whose coursenum begins with "3".
 *
 *
 * @author Gheo
 */
public class Operations_JDBC {

    private static Connection connection;

    static {
        try {
            connection = new JDBC_Connector().getConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to database... " + e.getMessage());

        }
    }

    /** Closes the connection to the database "school"
     *
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("something went wrong ->"+e.getMessage());
        }
    }

    /**
     *  Display data for all classes.
     */
    public void queryAllClasses() {

        Statement st = null;
        ResultSet rs = null;
        final String format = "%10s%10s%15s%15s%15s%10s%15s%25s%30s%20s%20s\n";
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select classes.classid, classes.courseid, classes.days, classes.starttime, " +
                    "classes.endtime, classes.bldg, classes.roomnum, courses.title, courses.descrip, crosslistings.dept, " +
                    "crosslistings.coursenum " +
                    "from classes inner join courses on classes.courseid = courses.courseid inner join crosslistings " +
                    "on classes.courseid = crosslistings.courseid");
            boolean hasResults = rs.next();
            if (hasResults) {
                System.out.format(format, "ClassId", "Course Id", "Days", "Start Time", "End Time", "bldg",
                        "Room Number", "Title", "Description", "department", "Course Number");
                do {
                    System.out.format(format,
                            rs.getString("classid"),
                            rs.getString("courseid"),
                            rs.getString("days"),
                            rs.getString("starttime"),
                            rs.getString("endtime"),
                            rs.getString("bldg"),
                            rs.getString("roomnum"),
                            rs.getString("title"),
                            rs.getString("descrip"),
                            rs.getString("dept"),
                            rs.getString("coursenum"));
                } while (rs.next());
            } else {
                System.out.println("No results");
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("something went wrong ->" + e.getMessage());
        }
        finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (st != null) try {
                st.close();
            } catch (SQLException e) {
            }

        }

    }

    /** Display data for a course with specified id.
     * @param id  the integer number expected by this method to search for the course with the "courseid" = id.
     */
    public void queryByCourseID(int id) {


        PreparedStatement prepared = null;
        ResultSet rs = null;
        final String format = "%15s%15s%25s%40s%25s\n";
        try {
            String query = "select * from courses where courseid =?";
            prepared = connection.prepareStatement(query);
            prepared.setString(1, Integer.toString(id));

            rs = prepared.executeQuery();
            boolean hasResults = rs.next();
            if (hasResults) {
                System.out.format(format, "Course Id", "Area", "Title", "Description", "Prerequisites",
                        "Room Number");
                do {
                    System.out.format(format, rs.getString("courseid"), rs.getString("area"),
                            rs.getString("title"), rs.getString("descrip"),
                            rs.getString("prereqs"));
                } while (rs.next());
            } else {
                System.out.println("No results for course id = " + id);
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("something went wrong ->" + e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (prepared != null) try {
                prepared.close();
            } catch (SQLException e) {
            }

        }

    }

    /** Display data for all classes whose title (when converted to all lowercase letters) begins with "intro".
     * @param name the name of the title the user is searching for
     */
    public void queryByCourseName(String name) {

        PreparedStatement prepared = null;
        ResultSet rs = null;
        final String format = "%15s%15s%15s%15s%25s%25S\n";
        try {
            String query = "select classes.classid, classes.days, classes.starttime, classes.endtime, courses.title," +
                    "courses.descrip from classes inner join courses on courses.courseid = classes.courseid " +
                    "where substr(lower((courses.title)),1, 5 ) = ? ;";
            prepared = connection.prepareStatement(query);
            prepared.setString(1, name);

            rs = prepared.executeQuery();
            boolean hasResults = rs.next();
            if (hasResults) {
                System.out.format(format, "Class Id", "Days", "Start Time", "End Time", "Title", "Description");
                do {
                    System.out.format(format,
                            rs.getString("classid"),
                            rs.getDate("days"),
                            rs.getTime("starttime"),
                            rs.getTime("endtime"),
                            rs.getString("title"),
                            rs.getString("descrip"));
                } while (rs.next());
            } else {
                System.out.println("No results for classes with course name starting with: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("something went wrong ->"+ e.getMessage());
        }
        finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (prepared != null) try {
                prepared.close();
            } catch (SQLException e) {
            }
        }
    }

    /** Display data for all classes whose dept (when converted to all lowercase letters) is "cos" and whose
     * coursenum begins with "3".
     * @param dept the name of the department the user is searching for
     */
    public void queryByDept(String dept) {
        PreparedStatement prepared = null;
        ResultSet rs = null;
        final String format = "%15s%15s%15s%15s%25s%25S%15s%15s\n";
        try {
            String query = "select classes.classid, classes.days, classes.starttime, classes.endtime, courses.title, " +
                    "courses.descrip, crosslistings.dept, crosslistings.coursenum " +
                    "from classes join courses " +
                    "on classes.courseid = courses.courseid " +
                    "join crosslistings " +
                    "on classes.courseid = crosslistings.courseid " +
                    "where lower(crosslistings.dept)= ? and " +
                    "crosslistings.coursenum-300>=0 and crosslistings.coursenum-300< 100;";
            prepared = connection.prepareStatement(query);
            prepared.setString(1, dept);

            rs = prepared.executeQuery();
            boolean hasResults = rs.next();
            if (hasResults) {
                System.out.format(format, "Class Id", "Days", "Start Time", "End Time", "Title", "Description",
                        " Deparment", "Course Number");
                do {
                    System.out.format(format,
                            rs.getString("classid"),
                            rs.getDate("days"),
                            rs.getTime("starttime"),
                            rs.getTime("endtime"),
                            rs.getString("title"),
                            rs.getString("descrip"),
                            rs.getString("dept"),
                            rs.getString("coursenum"));
                } while (rs.next());
            } else {
                System.out.println("No results for classes with course in department: " + dept);
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("something went wrong ->" +e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (prepared != null) try {
                prepared.close();
            } catch (SQLException e) {
            }
        }

    }


}
