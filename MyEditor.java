import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.Vector;
import java.util.regex.Matcher;

public class MyEditor implements ActionListener{
    //all window exits
    private void AllExits(){
        if(find!=null && find.isVisible()==true){
            find.setVisible(false);
            find.dispose();
        }
        if(findnreplace!=null && findnreplace.isVisible()==true){
            findnreplace.setVisible(false);
            findnreplace.dispose();
        }
    }

    //Window Closing
    class MainWindowCloser extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            File file = new File(path+FileName);
            if(t.getText().equals("")){
                Window w = e.getWindow();
                w.setVisible(false);
                w.dispose();
                AllExits();
            }
            //file is already in the system then
            else if(file.exists())
            {
                try
                {
                    //writing content of textarea into file which i have change in it
                    String str = t.getText();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(str);
                    bw.close();
                } 
                catch(IOException exp)
                {
                    System.out.println(exp.getMessage());
                }
                Window w = e.getWindow();
                w.setVisible(false);
                w.dispose();
                AllExits();
            } else {
                sve = new Frame();
                sve.setSize(500, 200);
                Label l = new Label("Do you want to Save this file?");
                sve.add(l,"North");
                Panel p = new Panel();
                Button yes = new Button("Yes");
                p.add(yes,"West");
                yes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1){
                        sve.setVisible(false);
                        saving(Save);
                        Window w = e.getWindow();
                        w.setVisible(false);
                        w.dispose();
                        AllExits();
                    }
                });
                Button no = new Button("No");
                p.add(no,"East");
                no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1){
                        sve.setVisible(false);
                        sve.dispose();
                        Window w = e.getWindow();
                        w.setVisible(false);
                        w.dispose();
                        AllExits();
                    }
                });
                sve.add(p,"South");
                sve.addWindowListener(new WindowCloser());
                sve.setVisible(true);
                //sve.dispose();
            }
        }
    }

    class WindowCloser extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            Window w = e.getWindow();
            w.setVisible(false);
            w.dispose();
        }
    }

    //Menu Button Actions
    public void actionPerformed(ActionEvent e){
        //Exit funtion
        if(e.getSource()==Exit)
        {
            ActionEvent saveEvent = new ActionEvent(Save, ActionEvent.ACTION_PERFORMED, "Save");
            actionPerformed(saveEvent);
            f.dispose();
            AllExits();
        } 
        //New Text Editor function
        else if(e.getSource()==New)
        {
            //new MyEditor();
            File file = new File(path+FileName);
            if(t.getText().equals("")){
                AllExits();
            }
            //file is already in the system then
            else if(file.exists())
            {
                try
                {
                    //writing content of textarea into file which i have change in it
                    String str = t.getText();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(str);
                    bw.close();
                } 
                catch(IOException exp)
                {
                    System.out.println(exp.getMessage());
                }
                AllExits();
                FileName = "Untitle*";
                f.setTitle(FileName);
                t.setText("");
            } else {
                sve = new Frame();
                sve.setSize(500, 200);
                Label l = new Label("Do you want to Save this file?");
                sve.add(l,"North");
                Panel p = new Panel();
                yes = new Button("Yes");
                p.add(yes,"West");
                yes.addActionListener(this);
                no = new Button("No");
                p.add(no,"East");
                no.addActionListener(this);
                sve.add(p,"South");
                sve.addWindowListener(new WindowCloser());
                sve.setVisible(true);
            }
        } 
        //opening function
        else if(e.getSource()==Open)
        {
            File file = new File(path+FileName);
            if(t.getText().equals("")){
                AllExits();
                //now opening new file
                open();  
            }
            //file is already in the system then
            else if(file.exists())
            {
                try
                {
                    //writing content of textarea into file which i have change in it
                    String str = t.getText();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(str);
                    bw.close();
                } 
                catch(IOException exp)
                {
                    System.out.println(exp.getMessage());
                }
                AllExits();
                FileName = "Untitle*";
                f.setTitle(FileName);
                t.setText("");
                //now opening new file
                open();
            } else {
                sve = new Frame();
                sve.setSize(500, 200);
                Label l = new Label("Do you want to Save this file?");
                sve.add(l,"North");
                Panel p = new Panel();
                Button yes = new Button("Yes");
                p.add(yes,"West");
                yes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1){
                        sve.setVisible(false);
                        sve.dispose();
                        saving(Save);
                        FileName = "Untitle*";
                        f.setTitle(FileName);
                        t.setText("");
                        //now opening new file
                        open();
                    }
                });
                Button no = new Button("No");
                p.add(no,"East");
                no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1){
                        sve.setVisible(false);
                        sve.dispose();
                        AllExits();
                        t.setText("");
                        //now opening new file
                        open();
                    }
                });
                sve.add(p,"South");
                sve.addWindowListener(new WindowCloser());
                sve.setVisible(true);
                //sve.dispose();
            }
        } 
        //saving function
        else if(e.getSource()==Save)
        {
            File file = new File(path+FileName);
            //file is already in the system then
            if(file.exists())
            {
                try
                {
                    //writing content of textarea into file which i have change in it
                    String str = t.getText();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(str);
                    bw.close();
                } 
                catch(IOException exp)
                {
                    System.out.println(exp.getMessage());
                }
            } 
            else 
            {
                saving(Save);
            }
        } 
        //SaveAs function
        else if(e.getSource()==SaveAs)
        {
            saving(SaveAs);
        }
        //find funtion
        else if(e.getSource()==Find)
        {
            find = new Frame();
            find.setSize(500, 200);

            Panel p1 = new Panel();
            Label l1 =  new Label("Find");
            p1.add(l1);
            TextField t1 = new TextField(20);
            p1.add(t1);
            find.add(p1,"North"); 

            Panel p2 =  new Panel();
            Button FindNext = new Button("Find Next");
            Vector<Integer> s = new Vector<>();
            Vector<Integer> ed = new Vector<>();
            Vector<Integer> selectstart = new Vector<>();
            FindNext.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1){
                    findingnext(s, ed, t1 , selectstart);
                }
            });
            p2.add(FindNext);
            Button close =  new Button("Close");
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1){
                    t.setSelectionStart(-1);
                    t.setSelectionEnd(-1);
                    s.clear();
                    ed.clear();
                    System.out.println(s);
                    System.out.println(ed);
                    find.setVisible(false);
                    find.dispose();
                }
            });
            p2.add(close);
            find.add(p2,"South");

            find.addWindowListener(new WindowCloser());
            find.setVisible(true);
        }
        //find and replace funtion 
        else if(e.getSource()==FindandReplace)
        {
            findnreplace = new Frame();
            findnreplace.setSize(500, 200);

            Panel p1 = new Panel();
            Label l1 =  new Label("Find");
            p1.add(l1);
            TextField t1 = new TextField(20);
            p1.add(t1);
            findnreplace.add(p1,"North"); 

            Panel p3 = new Panel();
            Panel p4 = new Panel();
            Label l2 = new Label("Replace");
            p4.add(l2);
            TextField t3 = new TextField(20);
            p4.add(t3);
            p3.add(p4,"North");
            findnreplace.add(p3);

            Panel p2 =  new Panel();
            Button FindNext = new Button("Find Next");
            Vector<Integer> s = new Vector<>();
            Vector<Integer> ed = new Vector<>();
            Vector<Integer> selectstart = new Vector<>();
            FindNext.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1){
                    findingnext(s, ed, t1, selectstart);
                }
            });
            p2.add(FindNext);
            Button replace = new Button("Replace");
            replace.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(!t1.getText().equals("") && !t3.getText().equals("")){
                        if(selectstart.size()>0){
                            String str = t.getText();
                            str = str.substring(0, selectstart.get(0))+t3.getText()+str.substring(selectstart.get(0)+t1.getText().length());
                            t.setText(str);
                            s.clear();ed.clear();
                            findingnext(s, ed, t1, selectstart);
                        } else if(selectstart.size()==0){
                            findingnext(s, ed, t1, selectstart);
                            String str = t.getText();
                            str = str.substring(0, selectstart.get(0))+t3.getText()+str.substring(selectstart.get(0)+t1.getText().length());
                            t.setText(str);
                            s.clear();ed.clear();
                            findingnext(s, ed, t1, selectstart);
                        }
                    }
                }
            });
            p2.add(replace);
            //finding and replacing all
            Button replaceall = new Button("ReplaceAll");
            replaceall.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(!t1.getText().equals("") && !t3.getText().equals("")){
                        t.setSelectionStart(-1);
                        t.setSelectionEnd(-1);
                        String temp = t.getText();
                        Pattern p = Pattern.compile(t1.getText());
                        Matcher m = p.matcher(temp);
                        while(m.find()){
                            //System.out.println(temp);
                            temp = temp.substring(0, m.start()) + t3.getText() + temp.substring(m.end());
                            t.setText(temp);
                            m = p.matcher(temp).region(m.start()+t3.getText().length(), temp.length());
                        }
                    } else{

                    }
                }
            });
            p2.add(replaceall);
            //closing find and replace frame
            Button close =  new Button("Close");
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1){
                    t.setSelectionStart(-1);
                    t.setSelectionEnd(-1);
                    s.clear();
                    ed.clear();
                    findnreplace.setVisible(false);
                    findnreplace.dispose();
                }
            });
            p2.add(close);
            findnreplace.add(p2,"South");

            findnreplace.addWindowListener(new WindowCloser());
            findnreplace.setVisible(true);
        }
        else if(e.getSource()==no){
            sve.setVisible(false);
            sve.dispose();
            AllExits();
            t.setText("");
        }
        else if(e.getSource()==yes){
            sve.setVisible(false);
            sve.dispose();
            saving(Save);
            FileName = "Untitle*";
            f.setTitle(FileName);
            t.setText("");
        }
    }

    Frame f,sve,find,findnreplace;
    TextArea t;
    MenuBar mb;
    Menu m1,m2;
    Button no,yes;
    MenuItem New,Open,Save,SaveAs,Exit,Find,FindandReplace;
    String FileName = "Untitle*";
    String path = "";
    public MyEditor(){
        f = new Frame(FileName);
        f.setSize(900, 500);
        //TextArea
        t = new TextArea();
        t.setFont(new Font("arial", Font.PLAIN, 24));
        f.add(t);
        //MenuBar
        mb = new MenuBar();
        f.setMenuBar(mb);
        //Menu
        m1 = new Menu("File");
        mb.add(m1);
        m2 = new Menu("Edit");
        mb.add(m2);
        //MenuItem
        New = new MenuItem("New");
        New.addActionListener(this);
        m1.add(New);
        Open = new MenuItem("Open");
        Open.addActionListener(this);
        m1.add(Open);
        Save = new MenuItem("Save");
        Save.addActionListener(this);
        m1.add(Save);
        SaveAs = new MenuItem("SaveAs");
        SaveAs.addActionListener(this);
        m1.add(SaveAs);
        m1.addSeparator();
        Exit = new MenuItem("Exit");
        Exit.addActionListener(this);
        m1.add(Exit);
        Find = new MenuItem("Find");
        Find.addActionListener(this);
        m2.add(Find);
        FindandReplace = new MenuItem("Find & Replace");
        FindandReplace.addActionListener(this);
        m2.add(FindandReplace);

        f.addWindowListener(new MainWindowCloser());
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MyEditor();
    }

    //saving main funtion
    public void saving(MenuItem item){
        FileDialog fd = new FileDialog(f,"Save",FileDialog.SAVE);
        fd.setVisible(true);
        File file = new File(path+FileName);
        String str = t.getText();
        if(fd.getDirectory()==null && fd.getFile()==null){
        } else{
            if(file.exists()){
                //file.delete();
                path = fd.getDirectory();
                FileName = fd.getFile();
                file = new File(path+FileName);
                try{
                    file.createNewFile();
                    BufferedWriter wc = new BufferedWriter(new FileWriter(file));
                    wc.write(str);
                    wc.close();
                } catch(IOException exc){
                    System.out.println(exc.getMessage());
                }
            } else {
                if(item == Save){
                    path = fd.getDirectory();
                    FileName = fd.getFile();
                    file = new File(path+FileName);
                }
                try{
                    file.createNewFile();
                    BufferedWriter wc = new BufferedWriter(new FileWriter(file));
                    wc.write(str);
                    wc.close();
                } catch(IOException exc){
                    System.out.println(exc.getMessage());
                }
            }
            f.setTitle(FileName);
        }
    }

    //findnext main funtion
    public void findingnext(Vector<Integer> s,Vector<Integer> ed,TextField t1,Vector<Integer> selectstart){
        if(!t1.getText().equals("")){
            Pattern p = Pattern.compile(t1.getText());
            Matcher m = p.matcher(t.getText());
            // if(s.size()>0 && (t1.getText().length()<(ed.elementAt(0)-s.elementAt(0)) ||
            // t1.getText().length()>(ed.elementAt(0)-s.elementAt(0))) ){
            //     s.clear();
            //     ed.clear();
            // }
            if(s.size()>0 && (!t1.getText().equals(t.getText().substring(s.elementAt(0), ed.elementAt(0))))){
                s.clear();
                ed.clear();
            }
            if(s.size()==0 || !t1.getText().equals(t.getText().substring(s.elementAt(0), ed.elementAt(0)))){
                while(m.find()){
                    if(s.size()>0){
                        if(!t1.getText().equals(t.getText().substring(s.elementAt(0), ed.elementAt(0)))){
                            s.clear();
                            ed.clear();
                        }
                    }
                    s.add(m.start());
                    ed.add(m.end());
                }
            }
            if(s.size()>0){
                t.setSelectionStart(s.get(0));
                selectstart.add(0, s.get(0));
                t.setSelectionEnd(ed.get(0));
                s.remove(0);
                ed.remove(0);
            } else {
                t.setSelectionStart(-1);
                t.setSelectionEnd(-1);
            }
        } else {
            t.setSelectionStart(-1);
            t.setSelectionEnd(-1);
        }
    }

    //file opening function
    private void open(){
        //Selecting file
        FileDialog fd = new FileDialog(f, "Open", 0);
        fd.setTitle("Select file to open");
        fd.setVisible(true);
        if(fd.getDirectory()==null && fd.getFile()==null){
        }else {
            path = fd.getDirectory();
            FileName = fd.getFile();
            f.setTitle(FileName);
            File file = new File(path+FileName);
            // System.out.println(path);
            // System.out.println(FileName);
            try
            {
                //content of file writing in textarea
                String str = "";
                BufferedReader bis = new BufferedReader(new FileReader(file));
                String oneline = bis.readLine(); 
                while(oneline != null){
                    str += oneline+"\n";
                    oneline = bis.readLine();
                }
                t.setText(str);
                bis.close();
            } 
            catch(Exception exc)
            {
                System.out.println(exc.getMessage());
            }
        }
    }
}
