package lendle.courses.wp.finalexam_wp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class TaskFrame extends JInternalFrame
{
    private JTextField textTitle = new JTextField();
    private JTextArea textContent = new JTextArea();
    private boolean modified = false;

    public TaskFrame()
    {
        this.setSize(500, 300);
        //Q4: layout 出如圖所示的樣子，
        //記得 JTextArea 要放在捲軸裡面 (30%)
        this.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        this.add(northPanel, "North");
        northPanel.setLayout(new GridLayout(1, 2));
        JLabel jLabel = new JLabel("Title：");
        textTitle.setEditable(false);
        northPanel.add(jLabel);
        northPanel.add(textTitle);
        
        JScrollPane jScrollPane = new JScrollPane();
        this.add(jScrollPane);
        jScrollPane.getViewport().add(textContent);
        ////////////////////////////
        this.setClosable(true);
        this.setResizable(true);
        this.setVisible(true);

        JPanel southPanel = new JPanel();
        this.add(southPanel, "South");
        JButton saveButton = new JButton("Save");
        southPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TaskDB.save(getNoteTitle(), getNoteContent());
                modified = false;
                setTitle("");
            }
        });

        KeyListener keyListener = new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                modified = true;
                setTitle("*");
            }

        };
        textContent.addKeyListener(keyListener);
        this.addInternalFrameListener(new InternalFrameAdapter()
        {
            @Override
            public void internalFrameClosing(InternalFrameEvent e)
            {
                if (modified)
                {
                    //Q5: 發現變更，顯示 confirm dialog 詢問是否要儲存 (20%)
                    int ret = JOptionPane.showConfirmDialog(TaskFrame.this, "是否要儲存?", "Note 未儲存", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    /////////////////////////////////////////////
                    if (ret == JOptionPane.YES_OPTION)
                    {
                        TaskDB.save(getNoteTitle(), getNoteContent());
                        modified = false;
                        setTitle("");
                    }
                }
            }
        });
    }

    public String getNoteTitle()
    {
        return textTitle.getText();
    }

    public void setNoteTitle(String title)
    {
        this.textTitle.setText(title);
    }

    public String getNoteContent()
    {
        return textContent.getText();
    }

    public void setNoteContent(String content)
    {
        this.textContent.setText(content);
    }
}