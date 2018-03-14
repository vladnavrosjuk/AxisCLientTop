import com.doszhan.CustomServiceStub;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.rmi.RemoteException;

public class CustomClient {

    private final static String END_POINT = "http://localhost:8080/axis2/services/CustomService?wsdl";

    public static void main(String[] args) {

    }
    public void  createStudent(String name,String surname, String group, String facultet, String srbal, String number){
        try {
            CustomServiceStub stub = new CustomServiceStub(END_POINT);
            CustomServiceStub.CreateStudent createStudent = new CustomServiceStub.CreateStudent();
            createStudent.setName(name);
            createStudent.setFacultet(facultet);
            createStudent.setGroup(group);
            createStudent.setSrbal(srbal);
            createStudent.setNubmer(number);
            createStudent.setSurname(surname);
            stub.createStudent(createStudent);
        }
        catch (RemoteException e1)
        {

        }

    }
    public void  deleteStudent(String name){
        try {
            CustomServiceStub stub = new CustomServiceStub(END_POINT);
            CustomServiceStub.DeleteStudentFromGui deleteStudentFromGui = new CustomServiceStub.DeleteStudentFromGui();
            deleteStudentFromGui.setNamefile(name);
            stub.deleteStudentFromGui(deleteStudentFromGui);
        }
        catch (RemoteException e1)
        {

        }
        //adben to java object
    }
    public String readFile(String filename){
     try {
         CustomServiceStub stub = new CustomServiceStub(END_POINT);
         CustomServiceStub.ReadFile readFile = new CustomServiceStub.ReadFile();
         readFile.setFilenames(filename);
         CustomServiceStub.ReadFileResponse readFileResponse = stub.readFile(readFile);
         String response = readFileResponse.get_return();
         return response;
     }
     catch (RemoteException e){
         return "Eror read";

     }

    }
    public String[] getNameFile(){
        try {
            CustomServiceStub stub = new CustomServiceStub(END_POINT);
            CustomServiceStub.GetFilesName getInfoFromSubsection = new CustomServiceStub.GetFilesName();

            CustomServiceStub.GetFilesNameResponse getInfoFromSubsection1 = stub.getFilesName(getInfoFromSubsection);
            String[] strings = getInfoFromSubsection1.get_return();

            return  strings;



        } catch (RemoteException e) {
            return  new String[]{};

        }

    }
    public String[] getPathFile(){
        try {
            CustomServiceStub stub = new CustomServiceStub(END_POINT);
            CustomServiceStub.GetFilesPath getFilesPath = new CustomServiceStub.GetFilesPath();

            CustomServiceStub.GetFilesPathResponse getFilesPathResponse = stub.getFilesPath(getFilesPath);
            String[] strings = getFilesPathResponse.get_return();

            return  strings;



        } catch (RemoteException e) {
            return new String[]{};
        }


    }









}