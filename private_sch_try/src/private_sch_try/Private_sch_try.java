package private_sch_try;

import Details.AssignmentDetails;
import Details.AssignmentsPerCourseDetails;
import Details.CourseDetails;
import Details.SchedulePerCourseDetails;
import Details.StudentDetails;
import Details.StudentsPerCourseDetails;
import Details.TrainerDetails;
import Details.TrainersPerCourseDetails;
import Details.UsersDetails;
import dao.AssignmentDao;
import dao.AssignmentsPerCourseDao;
import dao.AssignmentsPerStudentPerCourseDao;
import dao.Assignments_Per_Student_Per_CourseDao;
import dao.CourseDao;
import dao.SchedulePerCourseDao;
import dao.StudentDao;
import dao.UsersDao;
import dao.StudentsPerCourseDao;
import dao.TrainerDao;
import dao.TrainersPerCourseDao;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import users_login.HeadMaster;
import users_login.LogIn;
import users_login.StudentUsers;
import users_login.TrainerUsers;

public class Private_sch_try {

    public static void main(String[] args) throws ParseException {
//        AssignmentDao aDao = new AssignmentDao();
//       
//        List<Assignments> ass = aDao.getListOfAssignments();
//        System.out.println(ass);
        //TrainersPerCourseDao a=new TrainersPerCourseDao();
        // a.getTrainerPerCoursesById(1);

//       Assignments_Per_Student_Per_CourseDao a= new Assignments_Per_Student_Per_CourseDao();
//       ArrayList<Assignments_Per_Student_Per_Course> ll= a.getqqq();
        // Assignments_Per_Student_Per_Course mm=new Assignments_Per_Student_Per_Course();
//        System.out.println(ll);
//        for (Assignments_Per_Student_Per_Course aa : ll){
//            
//            //System.out.println(aa.getCourse()+" // " +aa.getStudents()+" // "+ aa.getAssignments());
//            System.out.println(aa.getCourse());
//            //ArrayList<Assignment> proto=aa.getAssignments();
//            //ArrayList<Student> deftero=aa.getStudents();
//            for(int i=0;i<aa.getAssignments().size();i++){
//                System.out.println(aa.getAssignments().get(i)+ "/");
//                //System.out.println(proto.get(i)+"/"+deftero.get(i));
//            }
//            System.out.println();
//        }
        // trainer iii) arxi
//        AssignmentsPerStudentPerCourseDao a=new AssignmentsPerStudentPerCourseDao();
//        ArrayList<AssignmentsPerStudentPerCourse> r=a.view_a_p_s_p_c();
//        for(AssignmentsPerStudentPerCourse e: r){
//            for(int i=0;i<e.getId().size();i++){
//            System.out.println(e.getId().get(i));
//            System.out.println(e.getAss().get(i));
//            System.out.println(e.getSt().get(i));
//            System.out.println(e.getC().get(i));
//            }
//        }
        //telos
        //trainer i) arxi
//        TrainersPerCourseDao q=new TrainersPerCourseDao();
//        TrainersPerCourseDao t = new TrainersPerCourseDao();
//        ArrayList<Course> qq=new ArrayList();
//        qq=q.getListOfCoursesByTrainer(2);
//        System.out.println(q.getTrainer(2));
//        for(Course c: qq){
//            System.out.println(c);
//        }
        //telos
//        StudentsPerCourseDao s = new StudentsPerCourseDao();
//        ArrayList<StudentsPerCourse> ss=new ArrayList();
//        ss=s.view_s_p_c();
//        System.out.println(ss);
//        for(StudentsPerCourse q: ss){
//            System.out.println(q.getCourse());
//            for(int i=0 ; i<q.getStudents().size();i++){
//                System.out.println(q.getStudents());
//            }
//        }
        // System.out.println(s.getStudentsPerCourseById(1));
        //System.out.println(s.getlistofidcourse());
        //ii)vlepi olous tous mathites ana courso (sto erotima b)
        //s.getListOfidCourse_and_seeStudentsPerCourse();
        //telos
//       for(Assignments_Per_Student_Per_Course c: ll){
//           for(int i=0;i<c.getAssignments().size();i++)
//            System.out.println(c.getCourse()+"//" +c.getAssignments()+"//"+c.getStudents());
//        }
        //erotima i) student
//    SchedulePerCourseDao apcd=new SchedulePerCourseDao();
//    apcd.erotima_i_student(1);
        //telos
        //erotima ii) student
//    AssignmentsPerCourseDao a=new AssignmentsPerCourseDao();
//    a.erotima_ii_student();
        //telos
//        AssignmentsPerStudentPerCourseDao a = new AssignmentsPerStudentPerCourseDao();
        //a.insertMarkTrainer(60, 1);
        //a.insertSubStudent(1, 1);
//dokimi gia courseDao********#############$$$$$$$$$$$$$$$$$$$
//        CourseDao x=new CourseDao();
//        ArrayList<Integer> loco=x.getListOfIdCourses();
//        for(Integer sf:loco){
//            System.out.println(sf);
//        }
//        ArrayList<Course> xx=x.getListOfCourses();
//        System.out.println(xx);
//        System.out.println(x.getCourseById(1));
//        System.out.println(x.makeDate("2019-03-03"));
//        x.insertCourse("a", "b", "c" , "2019-08-08", "2019-08-08");
//          x.deleteCourseById(5);
//            x.updateCourseById(6, "v", "vv", "vvv", "2019-22-22", "2020-22-22");
//telos******######$$$$$$$$$$$$$$$$
//dokimi gia assignmentDao*******############$$$$$$$$$$$$$
//    AssignmentDao ad=new AssignmentDao();   
//        System.out.println(ad.getListOfIdAssignments());
//        System.out.println(ad.getListOfAssignments());
//        System.out.println(ad.getAssignmentById(3));
//        ad.insertAssignment("loco", "loco", "2020-01-01 23:59:59", 20, 80);
//        ad.updateAssignmentById(6, "a", "aa",   "aaa", 1, 2);
//        ad.deleteAssignmentById(6);
//telos ***&&&&&&&&&&^^^^^^^^^^
//dokimi gia trainerDao******&&&&&&&&^^^^^^^^
//    TrainerDao tr=new TrainerDao();
//        System.out.println(tr.getListOfIdTrainers());
//        System.out.println(tr.getListOfTrainers());
//        System.out.println(tr.getTrainerById(1));
//        tr.insertTrainer("chris", "doulamis", "a");
//        tr.updateTrainerById(3, "c", "d", "rrr");
//        tr.deleteTrainerById(3);
//telos *****&&&&&&&&&&&&^^^^^^^^^
//dokimi gia studentDao******&&&&&&&&^^^^^^^^
//    StudentDao st=new StudentDao();
//        System.out.println(st.getListOfIdStudents());
//        System.out.println(st.getListOfStudents());
//        System.out.println(st.getStudentById(2));
//        st.insertStudent("a", "a", "1987-04-04", 1000);
//        st.updateStudentById(5, "b", "b", "2010-09-09", 40000);
//        st.deleteStudentById(5);
//telos************&&&&&&&&&&&&&^^^^^^^
//dokimi gia studentspercourseDao******&&&&&&&&^^^^^^^^
//        StudentsPerCourseDao spc = new StudentsPerCourseDao();
//        System.out.println(spc.getCourse(1));
//        System.out.println(spc.getStudent(1));
//        System.out.println(spc.getListOfCoursesByStudent(1));
//        spc.insertStudentsPerCourse(1, 2);
//        spc.deleteStudentsPerCourseById(8);
//        spc.getListOfidCourse_and_seeStudentsPerCourse();
//telos ^^^^^^^^^^*********&&&&&&&&&&&
//dokimi gia trainerspercourseDao******&&&&&&&&^^^^^^^^
//    TrainersPerCourseDao tpc=new TrainersPerCourseDao();
//        System.out.println(tpc.getListOfCoursesByTrainer(1));
//        tpc.insertTrainersPerCourse(3, 1);
//        tpc.deleteTrainersPerCourseById(5);
//telos %%%^^^^^^^^^^************
//dokimi gia assignmentspercourseDao******&&&&&&&&^^^^^^^^
//    AssignmentsPerCourseDao apc=new AssignmentsPerCourseDao();
//    apc.insertAssignmentsPerCourse(1, 2);
//    apc.deleteAssignmentsPerCourseById(5);
//        apc.erotima_ii_student();
//telos &&&&&&&******(((((((((((
//dokimi gia schedulespercourseDao******&&&&&&&&^^^^^^^^
//SchedulePerCourseDao shpc=new SchedulePerCourseDao();
//        System.out.println(shpc.getListIdOfCoursesByIdStudent(3));
//        shpc.erotima_i_student(1);
//        shpc.insertSchedulePerCourse("a", "a", "a", "a", "a", "a", "a" , 1);
//        shpc.deleteSchedulePerCourseById(6);
//        shpc.updateSchedulePerCourseById("algevra", "geom.", "analisi", "sinartisiaki", "grm.alg", "-", "-", 1);
//telos$$$$$$$$$$$%%%%%%%%&&&&&&*
//        Scanner sc = new Scanner(System.in);
//Student User login **exoume pari username kai pass kai epistrefi idstudent 
//   UsersDetails sud=new UsersDetails();
//   StudentUsers su= new StudentUsers();
//   su=sud.getStudentDetails(sc);
//   UsersDao qre=new UsersDao();
//   System.out.println(qre.getIdStudentByLogin(su));
//   int id=qre.getIdStudentByLogin(su);
//   StudentDao st=new StudentDao();
//   System.out.println("Welcome: " + st.getStudentById(id));
//telos$$$$$%%%%%%%%
//Trainer User Login
//    UsersDetails sud=new UsersDetails();
//    TrainerUsers tu=new TrainerUsers();
//    tu=sud.getTrainerDetails(sc);
//    UsersDao gre=new UsersDao();
//    System.out.println(gre.getIdTrainerByLogin(tu));
//    int id=gre.getIdTrainerByLogin(tu);
//    TrainerDao tr=new TrainerDao();
//    System.out.println("Welcom: " + tr.getTrainerById(id));
//telos *****&&&&&&&&&$$$$$$
//headmaster login
//        Scanner sc = new Scanner(System.in);
//        UsersDetails sud = new UsersDetails();
//        HeadMaster hm = new HeadMaster();
//        hm = sud.getHeadMasterDetails(sc);
//        UsersDao gre = new UsersDao();
//        //System.out.println(gre.getHeadMasterLogin(hm));
//        Boolean bool = gre.getHeadMasterLogin(hm);
//        if (bool == true) {
//            System.out.println("Welcom: HeadMaster");
//        } else {
//            System.out.println("den ise headmaster");
//        }
//telos *******&&&&&&&&&^^
//HeadMaster pernai course kai apothikevode
//    CourseDetails cd=new CourseDetails();
//    Course cou=new Course();
//    cou = cd.getCourseDetails(sc);
//    CourseDao couD=new CourseDao();
//    couD.insertCourse(cou);
        //telos  
        //headmaster perni trainer kai apothikevi
//    TrainerDetails cd=new TrainerDetails();
//    Trainer tr = new Trainer();
//    tr = cd.getTrainerDetails(sc);
//    TrainerDao trD=new TrainerDao();
//    trD.insertTrainer(tr);
        //telos
        //headmaster perni assignment kai apothikevi
//    AssignmentDetails cd=new AssignmentDetails();
//    Assignment ass = new Assignment();
//    ass = cd.getAssignmentDetails(sc);
//    AssignmentDao assD=new AssignmentDao();
//    assD.insertAssignment(ass);
        //telos
        //headmaster perni student kai pothikevi
//    StudentDetails cd=new StudentDetails();
//    Student stu = new Student();
//    stu = cd.getStudentDetails(sc);
//    StudentDao assD=new StudentDao();
//    assD.insertStudent(stu);
        //telos
//Students per course details  
//        StudentsPerCourseDetails spc_de =new StudentsPerCourseDetails();
//        spc_de.getStudentsPerCourseDetails(sc);
//telos
//assignments per course details
//    AssignmentsPerCourseDetails apc_de= new AssignmentsPerCourseDetails();
//    apc_de.getAssignmentsPerCourseDetails(sc);
//telos
//trainers + + + + details
//    TrainersPerCourseDetails tpc_de=new TrainersPerCourseDetails();
//    tpc_de.getTrainersPerCourseDetails(sc);
//telos
//schedule pre course details
//    SchedulePerCourseDao socdao= new SchedulePerCourseDao();
//    SchedulePerCourseDetails scpd=new SchedulePerCourseDetails();
//    SchedulePerCourse spcc = new SchedulePerCourse();
//    spcc=scpd.getSchedulePerCourseDetails(sc);
//    socdao.insertSchedulePerCourse(spcc);
//telos
        Scanner sc = new Scanner(System.in);
        System.out.println("If you a Head Master pres 1.");
        System.out.println("If you a Trainer pres 2.");
        System.out.println("If you a Student pres 3.");
        int num = sc.nextInt();
        LogIn user = new LogIn();
        switch (num) {
            case 1:
                user.HeadMasterLogIn(sc);
                do {
                    System.out.println("If you want to create Courses pres 1");
                    System.out.println("If you want to create Trainers press 2");
                    System.out.println("If you want to create Assignments press 3");
                    System.out.println("If you want to create Students press 4");
                    System.out.println("If you want to create Students Per Course press 5");
                    System.out.println("If you want to create Assignments per course press 6");
                    System.out.println("If you want to create Trainers Per Course press 7");
                    System.out.println("If you want to create Schedules Per Course pres 8");
                    num = sc.nextInt();
                    switch (num) {
                        case 1:
                            CourseDetails cd = new CourseDetails();
                            cd.CreateCourse(sc);
                            break;
                        case 2:
                            TrainerDetails td = new TrainerDetails();
                            td.CreateTrainer(sc);
                            break;
                        case 3:
                            AssignmentDetails ad = new AssignmentDetails();
                            ad.CreateAssignment(sc);
                            break;
                        case 4:
                            StudentDetails sd = new StudentDetails();
                            sd.CreateStudent(sc);
                            break;
                        case 5:
                            StudentsPerCourseDetails spc_de = new StudentsPerCourseDetails();
                            spc_de.getStudentsPerCourseDetails(sc);
                            break;
                        case 6:
                            AssignmentsPerCourseDetails apc_de = new AssignmentsPerCourseDetails();
                            apc_de.getAssignmentsPerCourseDetails(sc);
                            break;
                        case 7:
                            TrainersPerCourseDetails tpc_de = new TrainersPerCourseDetails();
                            tpc_de.getTrainersPerCourseDetails(sc);
                            break;
                        case 8:
                            SchedulePerCourseDetails sch_p_c = new SchedulePerCourseDetails();
                            sch_p_c.CreateSchedulePerCourse(sc);
                            break;
                        default:
                            break;
                    }

                    System.out.println("Head Master if you want to put again pres yes");
                    System.out.println("Else press any button");
                } while ("yes".equals(sc.next()));
                break;
            case 2:
                int idtrainer = user.TrainerLogIn(sc);
                do {
                    System.out.println("If you want to see all Your courses press 1");
                    System.out.println("If you want to see all the students per cours press 2");
                    System.out.println("If you want to see all the assignments per student per course press 3");
                    System.out.println("If you want to Mark any assignments per student per course press 4");
                    num = sc.nextInt();
                    switch (num) {
                        case 1:
//                            TrainersPerCourseDao tpc = new TrainersPerCourseDao();
//                            System.out.println(tpc.getListOfCoursesByTrainer(idtrainer));
                            TrainersPerCourseDao q = new TrainersPerCourseDao();
                            TrainersPerCourseDao t = new TrainersPerCourseDao();
                            ArrayList<Course> qq = new ArrayList();
                            qq = q.getListOfCoursesByTrainer(idtrainer);
                            System.out.println(q.getTrainer(idtrainer));
                            for (Course c : qq) {
                                System.out.println(c);
                            }
                            break;
                        case 2:
                            StudentsPerCourseDao st_p_c = new StudentsPerCourseDao();
                            st_p_c.getListOfidCourse_and_seeStudentsPerCourse();
                            break;
                        case 3:
                            AssignmentsPerStudentPerCourseDao a = new AssignmentsPerStudentPerCourseDao();
                            ArrayList<AssignmentsPerStudentPerCourse> r = a.view_a_p_s_p_c();
                            for (AssignmentsPerStudentPerCourse e : r) {
                                for (int i = 0; i < e.getId().size(); i++) {
                                    System.out.println(e.getId().get(i));
                                    System.out.println(e.getAss().get(i));
                                    System.out.println(e.getSt().get(i));
                                    System.out.println(e.getC().get(i));
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Give id AssignmentsPerStudentPerCourseDao");
                            int id_apspc = sc.nextInt();
                            System.out.println("Give a mark");
                            int mark = sc.nextInt();
                            AssignmentsPerStudentPerCourseDao apspc = new AssignmentsPerStudentPerCourseDao();
                            apspc.insertMarkTrainer(mark, id_apspc);
                            break;
                        default:
                            break;
                    }
                    System.out.println("If you want to see or submit again , pres yes");
                    System.out.println("Else press any button");
                } while ("yes".equals(sc.next()));
                break;
            case 3:
                int idstudent = user.StudentLogIn(sc);
                do {
                    System.out.println("If you want to see Your Schedule for your courses press 1");
                    System.out.println("If you want to see the dates of the assignments per course press 2");
                    System.out.println("If you want to subimt any assignment press 3");
                    num = sc.nextInt();
                    switch (num) {
                        case 1:
                            SchedulePerCourseDao apcd = new SchedulePerCourseDao();
                            apcd.erotima_i_student(idstudent);
                            break;
                        case 2:
                            AssignmentsPerCourseDao a = new AssignmentsPerCourseDao();
                            a.erotima_ii_student();
                            break;
                        case 3:
                            AssignmentDao assD=new AssignmentDao();
                            System.out.println(assD.getListOfIdAssignments());
                            System.out.println("Give the id of Assignment");
                            int idass=sc.nextInt();
                            AssignmentsPerStudentPerCourseDao apspc=new AssignmentsPerStudentPerCourseDao();
                            apspc.insertSubStudent(idstudent, idass);
                            break;
                        default:
                            break;
                    }
                    System.out.println("If you want to see or submit again , pres yes");
                    System.out.println("Else press any button");
                } while ("yes".equals(sc.next()));
                break;
            default:
                System.out.println("Den ise tipota apo ta tria");
                break;
        }

    }

}
