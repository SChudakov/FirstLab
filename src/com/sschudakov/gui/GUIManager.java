package com.sschudakov.gui;

import com.sschudakov.operations.*;
import com.sschudakov.operations.file_openers.FileOpener;
import com.sschudakov.operations.file_openers.HTMLFileOpener;
import com.sschudakov.operations.file_openers.TXTFileOpener;
import com.sschudakov.utils.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * Created by Semen Chudakov on 11.09.2017.
 */
public class GUIManager {

    private static final double GOLDEN_RATIO = 0.618034;

    private JFrame frame = new JFrame("File Manager");

    private JPanel panel = new JPanel();
//    private JPanel rightPanel = new JPanel();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu programMenu = new JMenu("Program");
    private JMenu fileMenu = new JMenu("File");
    private JMenu perspectiveMenu = new JMenu("Perspective");
    private JMenu operationsMenu = new JMenu("Operations");
    private JMenu helpMenu = new JMenu("Help");
    private JMenuItem showFilesItem = new JMenuItem("show references");
    private JMenuItem mergeItem = new JMenuItem("merge");
    private JMenuItem toUpperCaseItem = new JMenuItem("to uppercase");
    private JMenuItem findMatcherInText = new JMenuItem("find matches in text");
    private JMenuItem openItem = new JMenuItem("open");
    private JMenuItem closeItem = new JMenuItem("close");
    private JMenuItem saveItem = new JMenuItem("save");
    private JMenuItem renameItem = new JMenuItem("rename");
    private JMenuItem fileManagerPerspectiveItem = new JMenuItem("FileManager");
    private JMenuItem fileRedactorPerspectiveItem = new JMenuItem("FileRedactor");


    private DefaultMutableTreeNode leftJTreeTop = new DefaultMutableTreeNode("files");
    private DefaultMutableTreeNode rightJTreeTop = new DefaultMutableTreeNode("files");
    private JTree leftJTree = new JTree(leftJTreeTop);
    private JTree rightJTree = new JTree(rightJTreeTop);
    private JTextArea leftJTextArea = new JTextArea();
//    private JTextArea rightJTextArea = new JTextArea();
    private JScrollPane leftFilesAreaScrollPane = new JScrollPane(leftJTree);
    private JScrollPane rightFilesAreaScrollPane = new JScrollPane(rightJTree);
    private JScrollPane leftTextAreaScrollPane = new JScrollPane(leftJTextArea);
//    private JScrollPane rightTextAreaScrollPane = new JScrollPane(rightJTextArea);

    private JFileChooser jFileChooser = new JFileChooser();

    private Perspective perspective = Perspective.FileManager;

    private FileCloser fileCloser = new FileCloser(this.leftJTextArea);


    public void buildGUI() {

        setupFrame();

        setupPanels();

        setupMenuBar();

        setupScrollPanes();

        setupJTrees();

        addListeners();

        this.frame.setVisible(true);
    }

    private void setupFrame() {


        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setLayout(new GridBagLayout());
        this.frame.setSize(screenWidth * 8 / 10, screenHeight * 8 / 10);

        this.frame.add(this.panel, new GBC(0, 0, 1, 2, 1, 1, GridBagConstraints.BOTH));
//        this.frame.add(this.rightPanel, new GBC(1, 0, 1, 2, 0.5, 0.5, GridBagConstraints.BOTH));
    }

    private void setupMenuBar() {

        this.fileMenu.add(this.openItem);
        this.fileMenu.add(this.saveItem);
        this.fileMenu.add(this.closeItem);
        this.fileMenu.add(this.renameItem);

        this.operationsMenu.add(this.showFilesItem);
        this.operationsMenu.add(this.mergeItem);
        this.operationsMenu.add(this.toUpperCaseItem);
        this.operationsMenu.add(this.findMatcherInText);


        this.perspectiveMenu.add(this.fileManagerPerspectiveItem);
        this.perspectiveMenu.add(this.fileRedactorPerspectiveItem);

        this.menuBar.add(this.programMenu);
        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.operationsMenu);
        this.menuBar.add(this.helpMenu);
        this.menuBar.add(this.perspectiveMenu);

        this.frame.setJMenuBar(this.menuBar);
    }

    private void setupPanels() {

        GridBagLayout leftPanelLayout = new GridBagLayout();
//        GridBagLayout rightPanelLayout = new GridBagLayout();

        this.panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 1, true), "Panel"));
        this.panel.setLayout(leftPanelLayout);
        this.panel.add(this.leftFilesAreaScrollPane, new GBC(0, 0, 1, 2, 1 - GOLDEN_RATIO, 1 - GOLDEN_RATIO, GridBagConstraints.BOTH));
        this.panel.add(this.leftTextAreaScrollPane, new GBC(1, 0, 1, 2, GOLDEN_RATIO, GOLDEN_RATIO, GridBagConstraints.BOTH));

        this.panel.add(this.rightFilesAreaScrollPane, new GBC(2,0,1,2, 1 - GOLDEN_RATIO,1 - GOLDEN_RATIO, GridBagConstraints.BOTH));


//        this.rightPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 1, true), "Права"));
//        this.rightPanel.setLayout(rightPanelLayout);
//        this.rightPanel.add(this.rightFilesAreaScrollPane, new GBC(0, 0, 1, 2, 1 - GOLDEN_RATIO, 1 - GOLDEN_RATIO, GridBagConstraints.BOTH));
//        this.rightPanel.add(this.rightTextAreaScrollPane, new GBC(1, 0, 1, 2, GOLDEN_RATIO, GOLDEN_RATIO, GridBagConstraints.BOTH));


    }


    private void setupScrollPanes() {
        this.leftFilesAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.leftFilesAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.rightFilesAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.rightFilesAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.leftTextAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.leftTextAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        this.rightTextAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        this.rightTextAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setupJTrees() {

        JTreeBuilder leftJTreeBuilder = new JTreeBuilder(this.leftJTree, this.leftJTreeTop);
        JTreeBuilder rightJTreeBuilder = new JTreeBuilder(this.rightJTree, this.rightJTreeTop);

        this.leftJTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.rightJTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        this.leftJTree.addTreeSelectionListener(leftJTreeBuilder.new SelectionListener());
        this.rightJTree.addTreeSelectionListener(rightJTreeBuilder.new SelectionListener());

        leftJTreeBuilder.setupTree();
        rightJTreeBuilder.setupTree();


    }

    private void addListeners() {
        this.showFilesItem.addActionListener(new ShowFilesListener());
        this.mergeItem.addActionListener(new MergeListener());
        this.toUpperCaseItem.addActionListener(new ToUpperCaseListener());
        this.findMatcherInText.addActionListener(new FindMatchesInTextListener());

        this.openItem.addActionListener(new OpenFileListener());
        this.closeItem.addActionListener(new CloseListener());
        this.saveItem.addActionListener(new SaveListener());
        this.renameItem.addActionListener(new RenameListener());

        this.fileManagerPerspectiveItem.addActionListener(new FileManagerPerspectiveListener());
        this.fileRedactorPerspectiveItem.addActionListener(new FileRedactorPerspectiveListener());
    }


    class ShowFilesListener implements ActionListener {

        private static final String NEW_LINE_SYMBOL = "\n";

        @Override
        public void actionPerformed(ActionEvent e) {


            if (fileCloser.closeFile()) {
                if (perspective.equals(Perspective.FileRedactor)) {
                    DefaultMutableTreeNode leftSelectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                    if (leftSelectedNode == null) {
                        MessageRenderer.renderMessage(frame, "No one file has been selected");
                        return;
                    }

                    renderFilesList(leftJTextArea, leftSelectedNode);
                } else {
                    MessageRenderer.renderMessage(frame, "You cannot see files in File Manager perspective");
                }
            }
        }

        private void renderFilesList(JTextArea area, DefaultMutableTreeNode node) {

            String path = PathFormer.formPath(node);

            validatePath(path);
            List<String> listOfFiles = HTMLParser.parseFile(path);
            renderFilesList(area, listOfFiles);
        }

        private void renderFilesList(JTextArea area, List<String> list) {
            for (String line : list) {
                area.append(line + NEW_LINE_SYMBOL);
            }
        }

        private void validatePath(String path) {
            File pathFile = new File(path);

            if (!pathFile.exists()) {
                throw new IllegalArgumentException("there is no file or directory along the path:" + path);
            }

            if (pathFile.isDirectory()) {
                throw new IllegalArgumentException("path " + path + " points to a directory");
            }

            if (!isHTMLFile(pathFile.getName())) {
                throw new IllegalArgumentException("file along the path " + " is not a HTML file");
            }
        }

        private boolean isHTMLFile(String fileName) {
            return true;
        }

    }

    class MergeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {

                DefaultMutableTreeNode leftSelectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode rightSelectedNode = (DefaultMutableTreeNode) rightJTree.getLastSelectedPathComponent();

                if (leftSelectedNode == null || rightSelectedNode == null) {
                    MessageRenderer.renderMessage(frame, "One of the files for merging has not been selected");
                    return;
                }


                String first = PathFormer.formPath(leftSelectedNode);
                String second = PathFormer.formPath(rightSelectedNode);

                try {
                    validateSelectedFile(first);
                    validateSelectedFile(second);
                } catch (Exception e1) {
                    ExceptionRenderer.renderException(frame, e1);
                }

                jFileChooser.showSaveDialog(frame);

                String result = jFileChooser.getSelectedFile().getPath();

                try {
                    validateResultingFile(result);
                } catch (Exception e1) {
                    ExceptionRenderer.renderException(frame, e1);
                }

                FileMerger.mergeFiles(first, second, result);
            } else {
                MessageRenderer.renderMessage(frame, "You cannot merge files in File Redactor perspective");
            }
        }

        private void validateSelectedFile(String first) {
            File firstFile = new File(first);

            if (!firstFile.exists() || !firstFile.isFile()) {
                throw new IllegalArgumentException("There is no file along the given path:  " + first);
            }

        }

        private void validateResultingFile(String result) {

            File resultFile = new File(result);

            if (resultFile.exists()) {
                throw new IllegalArgumentException("Selected files cannot be merged to already elected file: " + result);
            }
        }
    }

    class ToUpperCaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileRedactor)) {
                leftJTextArea.setText(
                        leftJTextArea.getText().replace(
                                leftJTextArea.getSelectedText(), leftJTextArea.getSelectedText().toUpperCase()));
            } else {
                MessageRenderer.renderMessage(frame, "You cannot format text in File Manager perspective");
            }
        }
    }

    class FindMatchesInTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot find matches in text in File Manager perspective");
            } else {

                leftJTextArea.setSelectedTextColor(Color.BLUE);

                String inputPattern = UserTextInput.inputUserText(frame, "Type in a pattern");

                List<Substring> foundSubstrings = SubstringsFinder.findSubstrings(leftJTextArea.getText(), inputPattern);

                if (foundSubstrings.size() == 0) {
                    MessageRenderer.renderMessage(frame, "No matches have been found");
                }

                for (Substring substring : foundSubstrings) {
//                    System.out.println(substring.toString());
//                    leftJTextArea.select(substring.getBegin(), substring.getEnd());
                    leftJTextArea.setCaretPosition(substring.getBegin());
                    leftJTextArea.moveCaretPosition(substring.getEnd());

                }
            }
        }

    }


    class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot open files in File Manager perspective");
            } else {
                if (fileCloser.closeFile()) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                    if (selectedNode != null) {

                        FileOpener opener;

                        String path = PathFormer.formPath(selectedNode);

                        if (isHTMLFilePath(path)) {
                            opener = new HTMLFileOpener();
                            opener.openFile(path, leftJTextArea);
                        }
                        if (isTXTFilePath(path)) {
                            opener = new TXTFileOpener();
                            opener.openFile(path, leftJTextArea);
                        }

                        fileCloser.setOpenedFile(new File(path));

                    } else {
                        MessageRenderer.renderMessage(frame, "No one file has been selected");
                    }
                }
            }
        }

        private boolean isHTMLFilePath(String path) {
            return path.substring(path.length() - 5, path.length()).equals(".html");
        }

        private boolean isTXTFilePath(String path) {
            return path.substring(path.length() - 4, path.length()).equals(".txt");
        }
    }

    class CloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileCloser.closeFile();
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot save files in File Manager perspective");
            } else {

                jFileChooser.showSaveDialog(frame);
                File selectedFile = jFileChooser.getSelectedFile();

                try {
                    FileSaver.saveFile(leftJTextArea, selectedFile);
                } catch (Exception e1) {
                    ExceptionRenderer.renderException(frame, e1);
                }
            }
        }

    }

    class RenameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileRedactor)) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                if (selectedNode == null) {
                    MessageRenderer.renderMessage(frame, "No one file has been selected");
                } else {

                    String path = PathFormer.formPath(selectedNode);

                    try {
                        validatePath(path);
                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }

                    String newName = UserTextInput.inputUserText(frame, "Enter new name for the file");

                    try {
                        FileRenamer.renameFile(path, newName);
                        selectedNode.setUserObject(newName);
                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }

                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot rename files in File Merger perspective");
            }
        }

        private void validatePath(String path) {

            File pathFile = new File(path);

            if (!pathFile.exists()) {
                throw new IllegalArgumentException("There is no file or directory along the path:" + path);
            }
        }
    }


    class FileManagerPerspectiveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rightJTree.setEnabled(true);
            perspective = Perspective.FileManager;
        }
    }

    class FileRedactorPerspectiveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rightJTree.setEnabled(false);
            perspective = Perspective.FileRedactor;
        }
    }

}
