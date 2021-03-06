package com.sschudakov.gui;

import com.sschudakov.abstract_factory.factories.closers.TableFileCloser;
import com.sschudakov.abstract_factory.factories.openers.TableFileOpener;
import com.sschudakov.abstract_factory.factories.savers.TableSaver;
import com.sschudakov.abstract_factory.factories.views.FileView;
import com.sschudakov.abstract_factory.factories.views.TableFileView;
import com.sschudakov.abstract_factory.factories.views.TextFileView;
import com.sschudakov.abstract_factory.producer.FileViewProducer;
import com.sschudakov.operations.*;
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

    private JMenuBar menuBar = new JMenuBar();

    private JMenu programMenu = new JMenu("Program");
    private JMenu fileMenu = new JMenu("File");
    private JMenu folderMenu = new JMenu("Folder");
    private JMenu fileAndFolderMenu = new JMenu("File&Folder");
    private JMenu perspectiveMenu = new JMenu("Perspective");
    private JMenu operationsMenu = new JMenu("Operations");
    private JMenu helpMenu = new JMenu("Help");

    private JMenuItem showFilesItem = new JMenuItem("show references");
    private JMenuItem mergeItem = new JMenuItem("merge");
    private JMenuItem toUpperCaseItem = new JMenuItem("to uppercase");
    private JMenuItem findMatchesInText = new JMenuItem("find matches in text");
    private JMenuItem createTable = new JMenuItem("create table");

    private JMenuItem openItem = new JMenuItem("open");
    private JMenuItem closeItem = new JMenuItem("close");
    private JMenuItem saveItem = new JMenuItem("save");
    private JMenuItem createItem = new JMenuItem("create");
    private JMenuItem renameItem = new JMenuItem("rename");

    private JMenuItem createFolderItem = new JMenuItem("create");

    private JMenuItem deleteItem = new JMenuItem("delete");
    private JMenuItem copyItem = new JMenuItem("copy");
    private JMenuItem moveItem = new JMenuItem("move");

    private JMenuItem showAuthorInformationItem = new JMenuItem("show author information");
    private JMenuItem showInstructionsItem = new JMenuItem("show instructions");

    private JMenuItem fileManagerPerspectiveItem = new JMenuItem("FileManager");
    private JMenuItem fileRedactorPerspectiveItem = new JMenuItem("FileRedactor");

    private DefaultMutableTreeNode leftJTreeRoot = new DefaultMutableTreeNode("files");
    private DefaultMutableTreeNode rightJTreeRoot = new DefaultMutableTreeNode("files");
    private JTree leftJTree = new JTree(leftJTreeRoot);
    private JTree rightJTree = new JTree(rightJTreeRoot);
    private JTreeBuilder leftJTreeBuilder = new JTreeBuilder(this.leftJTree, this.leftJTreeRoot);
    private JTreeBuilder rightJTreeBuilder = new JTreeBuilder(this.rightJTree, this.rightJTreeRoot);


    private JTextArea mainJTextArea = new JTextArea();
    private JScrollPane leftFilesAreaScrollPane = new JScrollPane(leftJTree);
    private JScrollPane rightFilesAreaScrollPane = new JScrollPane(rightJTree);
    private JScrollPane leftTextAreaScrollPane = new JScrollPane(mainJTextArea);

    private JFileChooser jFileChooser = new JFileChooser();

    private Perspective perspective;

    private FileView fileView;


    public GUIManager() {
        FileViewProducer.setArea(this.mainJTextArea);
    }

    public void buildGUI() {

        setupFrame();

        setupPanels();

        setupMenuBar();

        setupScrollPanes();

        setupJTrees();

        addListeners();

        setupPerspective(Perspective.FileRedactor);

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

        this.frame.setJMenuBar(this.menuBar);
    }

    private void setupMenuBar() {

        this.fileMenu.add(this.openItem);
        this.fileMenu.add(this.closeItem);
        this.fileMenu.add(this.saveItem);
        this.fileMenu.add(this.createItem);

        this.folderMenu.add(this.createFolderItem);

        this.fileAndFolderMenu.add(this.deleteItem);
        this.fileAndFolderMenu.add(this.copyItem);
        this.fileAndFolderMenu.add(this.moveItem);
        this.fileAndFolderMenu.add(this.renameItem);

        this.operationsMenu.add(this.showFilesItem);
        this.operationsMenu.add(this.mergeItem);
        this.operationsMenu.add(this.toUpperCaseItem);
        this.operationsMenu.add(this.findMatchesInText);
        this.operationsMenu.add(this.createTable);

        this.helpMenu.add(this.showAuthorInformationItem);
        this.helpMenu.add(this.showInstructionsItem);

        this.perspectiveMenu.add(this.fileManagerPerspectiveItem);
        this.perspectiveMenu.add(this.fileRedactorPerspectiveItem);


        this.menuBar.add(this.programMenu);
        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.folderMenu);
        this.menuBar.add(this.fileAndFolderMenu);
        this.menuBar.add(this.operationsMenu);
        this.menuBar.add(this.helpMenu);
        this.menuBar.add(this.perspectiveMenu);
    }

    private void setupPanels() {

        GridBagLayout leftPanelLayout = new GridBagLayout();

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
    }

    private void setupJTrees() {
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
        this.findMatchesInText.addActionListener(new FindMatchesInTextListener());
        this.createTable.addActionListener(new CreateTableListener());

        this.openItem.addActionListener(new OpenFileListener());
        this.closeItem.addActionListener(new CloseListener());
        this.saveItem.addActionListener(new SaveListener());
        this.createItem.addActionListener(new CreateListener());

        this.createFolderItem.addActionListener(new CreateFolderListener());

        this.deleteItem.addActionListener(new DeleteListener());
        this.renameItem.addActionListener(new RenameListener());
        this.copyItem.addActionListener(new CopyListener());
        this.moveItem.addActionListener(new MoveListener());

        this.showAuthorInformationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageRenderer.renderMessage(frame,"This program is created by Semen Chudakov from group K-24 ");
            }
        });
        this.showInstructionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageRenderer.renderMessage(frame, "Welcome to File Manager!\n" +
                        "1) The app has to perspective respectively to operations with file you can perform:\n" +
                        "In File Redactor you can: create, delete, rename file and do with their content whatever is possible\n" +
                        "In File Manager perspective you can: merge, copy and move files\n" +
                        "2) File option on the Menu Bar contains operations you can do with files\n" +
                        "Folder option on the Menu Bar contains operations you can do with folders\n" +
                        "File&Folder option on the Menu Bar contains operations you can do with both files and folders\n" +
                        "Operations option on the Menu Bar contains operations that were my personal assignment\n" +
                        "In Perspective option on the Menu Bar you can switch perspective you wanna use");
            }
        });

        this.fileManagerPerspectiveItem.addActionListener(new FileManagerPerspectiveListener());
        this.fileRedactorPerspectiveItem.addActionListener(new FileRedactorPerspectiveListener());
    }

    private void setupPerspective(Perspective perspective) {

        this.perspective = perspective;

        if (perspective.equals(Perspective.FileManager)) {
            rightJTree.setEnabled(true);
        }
        if (perspective.equals(Perspective.FileRedactor)) {
            rightJTree.setEnabled(false);
        }
    }


    private boolean closeCurrentView() {
        if (this.fileView == null) {
            return true;
        }
        return this.fileView.close();
    }


    class ShowFilesListener implements ActionListener {

        private static final String NEW_LINE_SYMBOL = "\n";

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileRedactor)) {
                if (closeCurrentView()) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                    if (selectedNode != null) {
                        renderFilesList(mainJTextArea, selectedNode);
                    } else {
                        MessageRenderer.renderMessage(frame, "No one file has been selected");
                    }
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot see files in File Manager perspective");
            }
        }

        private void renderFilesList(JTextArea area, DefaultMutableTreeNode node) {

            String path = PathFormer.formPath(node);

            validatePath(path);

            List<String> listOfFiles = HTMLParser.parseFile(path);

            renderFilesList(area, listOfFiles);
        }

        private void renderFilesList(JTextArea area, List<String> list) {

            if (list.size() != 0) {
                for (String line : list) {
                    area.append(line + NEW_LINE_SYMBOL);
                }
            } else {
                MessageRenderer.renderMessage(frame, "File contains no hypertext references to other html files");
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

            if (!FileExtensionDeterminer.isHTNLFile(pathFile.getName())) {
                throw new IllegalArgumentException("file along the path " + path + " is not a HTML file");
            }
        }


    }

    class MergeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {

                DefaultMutableTreeNode leftSelectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode rightSelectedNode = (DefaultMutableTreeNode) rightJTree.getLastSelectedPathComponent();

                if (leftSelectedNode != null && rightSelectedNode != null) {

                    String first = PathFormer.formPath(leftSelectedNode);
                    String second = PathFormer.formPath(rightSelectedNode);

                    try {

                        validateSelectedFile(first);
                        validateSelectedFile(second);

                        jFileChooser.showSaveDialog(frame);

                        String result = jFileChooser.getSelectedFile().getPath();

                        if (result != null) {
                            validateResultingFile(result);

                            FileMerger.mergeFiles(first, second, result);

                            leftJTreeBuilder.addNode(result);
                            rightJTreeBuilder.addNode(result);
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        ExceptionRenderer.renderException(frame, e1);
                    }

                } else {
                    MessageRenderer.renderMessage(frame, "One of the files for merging has not been selected");
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot merge files in File Redactor perspective");
            }
        }

        private void validateSelectedFile(String selectedFile) {

            File firstFile = new File(selectedFile);

            if (!firstFile.exists() || !firstFile.isFile()) {
                throw new IllegalArgumentException("There is no file along the given path:  " + selectedFile);
            }

        }

        private void validateResultingFile(String result) {

            File resultFile = new File(result);

            if (resultFile.exists()) {
                throw new IllegalArgumentException("Selected files cannot be merged to already existing file: " + result);
            }
        }
    }

    class ToUpperCaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileRedactor)) {
                try {
                    ToUpperCaseFormatter.selectedTextToUpperCase(mainJTextArea);
                } catch (Exception e1) {
                    ExceptionRenderer.renderException(frame, e1);
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot format text in File Manager perspective");
            }
        }
    }

    class FindMatchesInTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileRedactor)) {

                String inputPattern = UserTextInput.inputUserText(frame, "Type in a pattern");


                if (inputPattern != null) {
                    System.out.println("pattern; " + inputPattern + " " + inputPattern.length());

                    List<String> foundWords = SimilarWordsFinder.findSimilarWords(mainJTextArea.getText(), inputPattern);

                    System.out.println("found words: " + foundWords.toString());

                    if (foundWords.size() == 0) {
                        MessageRenderer.renderMessage(frame, "There is no matches in text");
                    }

                    if (foundWords.size() == 1) {
                        MessageRenderer.renderMessage(frame, "The most similar word: " + foundWords.get(0));
                    }

                    if (foundWords.size() > 1) {
                        MessageRenderer.renderMessage(frame, "There are several similar words in text: "
                                + foundWords.toString());
                    }
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot find matches in text in File Manager perspective");
            }
        }

    }


    class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot open files in File Manager perspective");
            } else {
                if (closeCurrentView()) {

                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                    if (selectedNode != null) {

                        try {
                            String path = PathFormer.formPath(selectedNode);
                            File pathFile = new File(path);

                            FileView view = FileViewProducer.produceView(pathFile);
                            view.open();
                            if (view instanceof TextFileView) {
                                fileView = view;
                            }

                        } catch (Exception e1) {
                            ExceptionRenderer.renderException(frame, e1);
                            e1.printStackTrace();
                        }
                    } else {
                        MessageRenderer.renderMessage(frame, "No one file has been selected");
                    }
                }else {
                    MessageRenderer.renderMessage(frame, "No one file jas been selected");
                }
            }
        }
    }

    class CloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileView != null) {
                if (fileView.close()) {
                    fileView = null;
                }
            } else {
                MessageRenderer.renderMessage(frame, "NO one file is opened");
            }
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot save files in File Manager perspective");
            } else {
                if (fileView != null) {
                    fileView.save();
                } else {
                    MessageRenderer.renderMessage(frame, "NO one file is opened");
                }
            }
        }
    }

    class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot create files in File Manager perspective");
            } else {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                if (selectedNode != null) {

                    String path = PathFormer.formPath(selectedNode);

                    try {
                        validatePath(path);

                        String name = UserTextInput.inputUserText(frame, "Input name for the file");

                        if (name != null) {
                            FileCreator.createFile(path + "\\" + name);
                            leftJTreeBuilder.insertNodeInto(selectedNode, new DefaultMutableTreeNode(name));
                            rightJTreeBuilder.insertNodeInto(selectedNode, new DefaultMutableTreeNode(name));
                        }

                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }
                }
            }
        }

        private void validatePath(String path){

            File pathFile = new File(path);
            if(!pathFile.isDirectory()){
                throw new IllegalArgumentException("There is no directory along the path: " + path);
            }
        }
    }


    class RenameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileRedactor)) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                if (selectedNode != null) {

                    String path = PathFormer.formPath(selectedNode);

                    try {
                        String newName = UserTextInput.inputUserText(frame, "Enter new name for the file");

                        if (newName != null) {
                            FileRenamer.renameFile(path, newName);
                            leftJTreeBuilder.changeName(NodeFormer.formNode(path), newName);
                            rightJTreeBuilder.changeName(NodeFormer.formNode(path), newName);
                        }

                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }
                } else {
                    MessageRenderer.renderMessage(frame, "No one file has been selected");
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot rename files in File Merger perspective");
            }
        }
    }


    class CreateFolderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileRedactor)) {

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                if (selectedNode != null) {
                    String path = PathFormer.formPath(selectedNode);

                    try {
                        validatePath(path);

                        String name = UserTextInput.inputUserText(frame, "Input name for the folder");

                        if (name != null) {

                            FolderCreator.createFolder(path + "\\" + name);

                            leftJTreeBuilder.insertNodeInto(selectedNode, new DefaultMutableTreeNode(name));
                            rightJTreeBuilder.insertNodeInto(selectedNode, new DefaultMutableTreeNode(name));
                        }

                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }
                }

            } else {
                MessageRenderer.renderMessage(frame, "You cannot create folders in File Manager perspective");
            }
        }

        private void validatePath(String path) {

            File pathFile = new File(path);
            if (!pathFile.isDirectory()) {
                throw new IllegalArgumentException("There is no directory along the path: " + path);
            }
        }
    }


    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {
                MessageRenderer.renderMessage(frame, "You cannot delete files in File Manager perspective");
            } else {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();

                if (selectedNode != null) {

                    String path = PathFormer.formPath(selectedNode);

                    try {

                        int confirmed = OptionConfirmer.confirmOption(frame, "Are you sure you wanna delete this file?");

                        if (confirmed == 0) {
                            FileDeleter.delete(path);
                            String selectedNodePath = PathFormer.formPath(selectedNode);
                            leftJTreeBuilder.removeNodeFromParent(selectedNodePath);
                            rightJTreeBuilder.removeNodeFromParent(selectedNodePath);
                        }

                        if (confirmed == 1 || confirmed == 2 || confirmed == -1) {
                           /*NOP*/
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        ExceptionRenderer.renderException(frame, e1);
                    }
                }
            }
        }
    }

    class CopyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (perspective.equals(Perspective.FileManager)) {

                DefaultMutableTreeNode leftSelectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode rightSelectedNode = (DefaultMutableTreeNode) rightJTree.getLastSelectedPathComponent();

                if (leftSelectedNode != null && rightSelectedNode != null) {

                    String from = PathFormer.formPath(leftSelectedNode);
                    String to = PathFormer.formPath(rightSelectedNode);

                    try {
                        FileCopy.copy(from, to);
                        leftJTreeBuilder.insertNodeInto(rightSelectedNode, leftSelectedNode);
                        leftJTreeBuilder.reload(rightSelectedNode);
                        rightJTreeBuilder.insertNodeInto(rightSelectedNode,leftSelectedNode);
                        rightJTreeBuilder.reload(rightSelectedNode);
                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }

                } else {
                    if (leftSelectedNode == null) {
                        MessageRenderer.renderMessage(frame, "You haven`t selected file to be copied");
                    }
                    if (rightSelectedNode == null) {
                        MessageRenderer.renderMessage(frame, "You haven`t selected folder where file should be copied");
                    }
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot copy files in File Redactor perspective");
            }
        }
    }

    class MoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (perspective.equals(Perspective.FileManager)) {

                DefaultMutableTreeNode leftSelectedNode = (DefaultMutableTreeNode) leftJTree.getLastSelectedPathComponent();
                DefaultMutableTreeNode rightSelectedNode = (DefaultMutableTreeNode) rightJTree.getLastSelectedPathComponent();

                if (leftSelectedNode != null && rightSelectedNode != null) {

                    String from = PathFormer.formPath(leftSelectedNode);
                    String to = PathFormer.formPath(rightSelectedNode);

                    DefaultMutableTreeNode leftParent = (DefaultMutableTreeNode) leftSelectedNode.getParent();
                    DefaultMutableTreeNode rightParent = (DefaultMutableTreeNode) rightSelectedNode.getParent();

                    try {
                        FileMove.move(from, to);
                        leftJTreeBuilder.insertNodeInto(rightSelectedNode, leftSelectedNode);
                        leftJTreeBuilder.reload(rightSelectedNode);
                        rightJTreeBuilder.insertNodeInto(rightSelectedNode, leftSelectedNode);
                        rightJTreeBuilder.reload(rightSelectedNode);

                        leftJTreeBuilder.removeNodeFromParent(from);
                        leftJTreeBuilder.reload(leftParent);
                        rightJTreeBuilder.removeNodeFromParent(from);
//                        rightJTreeBuilder.reload(rightParent);

                    } catch (Exception e1) {
                        ExceptionRenderer.renderException(frame, e1);
                    }

                } else {
                    if (leftSelectedNode == null) {
                        MessageRenderer.renderMessage(frame, "You haven`t selected file to be moved");
                    }
                    if (rightSelectedNode == null) {
                        MessageRenderer.renderMessage(frame, "You haven`t selected folder where file should be moved");
                    }
                }
            } else {
                MessageRenderer.renderMessage(frame, "You cannot move files in File Redactor perspective");
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


    class CreateTableListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new TableFileView(new TableFileOpener(null), new TableFileCloser(new TableSaver(null))).open();
            } catch (Exception e1) {
                e1.printStackTrace();
                ExceptionRenderer.renderException(frame, e1);
            }
        }
    }
}
