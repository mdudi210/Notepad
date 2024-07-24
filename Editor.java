import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;;
public class Editor implements ActionListener {

    Frame f;
    MenuBar mb;
    Menu m1,m2;
    MenuItem nw,opn,sve,sveas,fid,fidnreplace,ext;
    TextArea t;
    String currFileName = null;

    //Opening file
    public class Opening implements ActionListener{
        Frame opening; 
        public void actionPerformed(ActionEvent e){
            
        }
        public Opening(){
            opening = new Frame("Open File");
            opening.setSize(900, 500);
            WindowCloser wc = new WindowCloser();
            opening.addWindowListener(wc);
            opening.setVisible(true);
        }
    }

    //Save file funtion
    public class Savefile implements ActionListener{
        Frame save;
        Button s,ex;
        TextField filename;
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == s){
                File file = new File(filename.getText());
                try{
                    file.createNewFile();
                    String str = t.getText();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(str);
                    bw.close();
                    save.dispose();
                } catch (Exception exp){
                    System.out.println(exp.getMessage());
                }
            } else if(e.getSource() == ex){
                save.dispose();
            }
        }
        public Savefile(){
            save = new Frame("Save");
            save.setSize(400,200);
            //design
            Panel p = new Panel();
            p.setLayout(new GridBagLayout());
            s = new Button("Save");
            s.addActionListener(this);
            ex = new Button("Exit");
            ex.addActionListener(this);
            filename = new TextField(20);
            p.add(s);
            p.add(filename);
            p.add(ex);
            save.add(p,"South");

            WindowCloser wc = new WindowCloser();
            save.addWindowListener(wc);

            save.setVisible(true);
        }
    }

    //finding words
    public class Finding{
        Frame finding;
        Button findnext,close;
        TextField find;
        public Finding(){
            finding = new Frame("Find");
            finding.setSize(500,500);
            WindowCloser wc = new WindowCloser();
            finding.addWindowListener(wc);
    
            Label l = new Label("Find");
            finding.add(l,"North");
            Panel p = new Panel(new BorderLayout());
            Panel p1 = new Panel(new BorderLayout());
            find = new TextField(20);
            p1.add(find,"North");
            p.add(p1,"West");
            finding.add(p);
    
            Panel p2 = new Panel(new BorderLayout());
            findnext =  new Button("Find Next");
            p2.add(findnext,"West");
            close = new Button("Close");
            p2.add(close,"East");
    
            finding.add(p2,"South");

            finding.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == nw){
            Editor e1 = new Editor();
        } else if(e.getSource() == opn){
            // Opening o = new Opening();
            Dialog fd =  new FileDialog(f,"Open",FileDialog.LOAD);
        } else if(e.getSource() ==  sve){
            Savefile s = new Savefile(); 
        } else if(e.getSource() == sveas){

        } else if(e.getSource() == ext){
            f.dispose();
        } else if(e.getSource() == fid){
            Finding id = new Finding();
        } else if(e.getSource() ==  fidnreplace){ 
        }
    }

    public Editor(){
        f = new Frame("Editor");
        f.setSize(900, 500);
        WindowCloser wc = new WindowCloser();
        f.addWindowListener(wc); 

        mb = new MenuBar();
        f.setMenuBar(mb);
        m1 = new Menu("File");
        mb.add(m1);
        m2 = new Menu("Edit");
        mb.add(m2);

        nw = new MenuItem("New");
        nw.addActionListener(this);
        m1.add(nw);
        opn = new MenuItem("Open");
        opn.addActionListener(this);
        m1.add(opn);
        sve = new MenuItem("Save");
        sve.addActionListener(this);
        m1.add(sve);
        sveas= new MenuItem("SaveAs");
        sveas.addActionListener(this);
        m1.add(sveas);
        m1.addSeparator();
        ext = new MenuItem("Exit");
        ext.addActionListener(this);
        m1.add(ext);

        fid = new MenuItem("Find");
        fid.addActionListener(this);
        m2.add(fid);
        fidnreplace = new MenuItem("Find & Replace");
        fidnreplace.addActionListener(this);
        m2.add(fidnreplace);

        t = new TextArea();
        f.add(t);

        f.setVisible(true);
    }
    public static void main(String[] args) {
        Editor e = new Editor();
    }
}