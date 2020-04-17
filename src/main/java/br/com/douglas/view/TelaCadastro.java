package br.com.douglas.view;

import br.com.douglas.model.dao.PessoaDAO;
import br.com.douglas.model.pojo.Pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class TelaCadastro extends JFrame {

    public static final Logger LOGGER = Logger.getLogger(TelaCadastro.class.getName());

    private JButton btnAtualizar;

    private JButton btnCadastrar;

    private JButton btnExcluir;

    private JLabel jLabel1;

    private JLabel jLabel2;

    private JPanel jPanel1;

    private JScrollPane jScrollPane1;

    private JTable jTPessoa;

    private JTextField txtEmail;

    private JTextField txtNome;

    public TelaCadastro() {
        this.initComponents();
        DefaultTableModel modelo = (DefaultTableModel) this.jTPessoa.getModel();
        this.jTPessoa.setRowSorter(new TableRowSorter<>(modelo));
        this.readjTable();
    }

    public void readjTable() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTPessoa.getModel();
        modelo.setNumRows(0);
        PessoaDAO pessoa = new PessoaDAO();
        pessoa.read().forEach((p) -> {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getEmail()});
        });
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.txtEmail = new JTextField();
        this.txtNome = new JTextField();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.btnCadastrar = new JButton();
        this.btnAtualizar = new JButton();
        this.btnExcluir = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jTPessoa = new JTable();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("CADASTRO");
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        this.txtEmail.addActionListener(TelaCadastro.this::txtEmailActionPerformed);
        this.txtNome.addActionListener(TelaCadastro.this::txtNomeActionPerformed);

        this.jLabel1.setText("Nome");
        this.jLabel2.setText("Email");
        this.btnCadastrar.setText("Cadastrar");
        this.btnCadastrar.addActionListener(TelaCadastro.this::btnCadastrarActionPerformed);
        this.btnAtualizar.setText("Atualizar");
        this.btnAtualizar.addActionListener(TelaCadastro.this::btnAtualizarActionPerformed);
        this.btnExcluir.setText("Excluir");
        this.btnExcluir.addActionListener(TelaCadastro.this::btnExcluirActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(LEADING, false).addComponent(this.txtNome, -1, 309, 32767).addComponent(this.txtEmail))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.btnCadastrar, -2, 93, -2).addGap(18, 18, 18).addComponent(this.btnExcluir, -2, 93, -2).addGap(18, 18, 18).addComponent(this.btnAtualizar, -2, 92, -2))).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(BASELINE).addComponent(this.txtNome, -2, -1, -2).addComponent(this.jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(BASELINE).addComponent(this.txtEmail, -2, -1, -2).addComponent(this.jLabel2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(BASELINE).addComponent(this.btnCadastrar).addComponent(this.btnAtualizar).addComponent(this.btnExcluir)).addContainerGap(15, 32767)));

        this.jTPessoa.setModel(new DefaultTableModel(new Object[0][], new String[]{"ID", "Nome", "Email"}) {
            boolean[] canEdit = new boolean[]{false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });

        this.jTPessoa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                TelaCadastro.this.jTPessoaMouseClicked(evt);
            }
        });

        this.jTPessoa.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                TelaCadastro.this.jTPessoaKeyReleased(evt);
            }
        });

        this.jScrollPane1.setViewportView(this.jTPessoa);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jScrollPane1, -1, 1003, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 331, -2).addGap(35, 35, 35)));
        this.pack();
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        Pessoa pessoa = new Pessoa(txtNome.getText(), txtEmail.getText());

        PessoaDAO dao = new PessoaDAO();
        dao.create(pessoa);

        this.txtNome.setText("");
        this.txtEmail.setText("");
        this.readjTable();
    }

    private void txtNomeActionPerformed(ActionEvent evt) {
    }

    private void txtEmailActionPerformed(ActionEvent evt) {
    }

    private void jTPessoaMouseClicked(MouseEvent evt) {
        if (this.jTPessoa.getSelectedRow() != -1) {
            this.txtNome.setText(this.jTPessoa.getValueAt(this.jTPessoa.getSelectedRow(), 1).toString());
            this.txtEmail.setText(this.jTPessoa.getValueAt(this.jTPessoa.getSelectedRow(), 2).toString());
        }

    }

    private void jTPessoaKeyReleased(KeyEvent evt) {
        if (this.jTPessoa.getSelectedRow() != -1) {
            this.txtNome.setText(this.jTPessoa.getValueAt(this.jTPessoa.getSelectedRow(), 1).toString());
            this.txtEmail.setText(this.jTPessoa.getValueAt(this.jTPessoa.getSelectedRow(), 2).toString());
        }

    }

    private void btnAtualizarActionPerformed(ActionEvent evt) {
        if (this.jTPessoa.getSelectedRow() != -1) {

            Integer id = (Integer) jTPessoa.getValueAt(jTPessoa.getSelectedRow(), 0);

            Pessoa pessoa = new Pessoa(id, txtNome.getText(), txtEmail.getText());

            PessoaDAO dao = new PessoaDAO();
            dao.update(pessoa);

            this.txtNome.setText("");
            this.txtEmail.setText("");
            this.readjTable();
        }

    }

    private void btnExcluirActionPerformed(ActionEvent evt) {
        if (this.jTPessoa.getSelectedRow() != -1) {
            PessoaDAO dao = new PessoaDAO();
            Pessoa pessoa = new Pessoa();
            pessoa.setId((Integer) this.jTPessoa.getValueAt(this.jTPessoa.getSelectedRow(), 0));
            dao.delete(pessoa);
            this.txtNome.setText("");
            this.txtEmail.setText("");
            this.readjTable();
        } else {
            JOptionPane.showMessageDialog(this.jPanel1, "Selecione um produto para excluir");
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();

            for (UIManager.LookAndFeelInfo info : var1) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LOGGER.log(SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new TelaCadastro().setVisible(true));
    }
}
