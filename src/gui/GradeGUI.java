package campusmatch.gui;

import campusmatch.dominio.*;
import campusmatch.enums.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GradeGUI extends JFrame {

    // Paleta de cores da tela
    private static final Color FUNDO = new Color(255, 255, 255);
    private static final Color FUNDO_PAINEL = new Color(235, 250, 238);
    private static final Color ROXO = new Color(76, 175, 80);
    private static final Color ROSA = new Color(56, 142, 60);
    private static final Color TEXTO = new Color(30, 30, 30);
    private static final Color VERMELHO_ALERTA = new Color(198, 40, 40);

    // Horários fixos do escopo: 2 dias da semana x 3 períodos
    private static final List<Horario> HORARIOS_DISPONIVEIS = List.of(
            new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.PRIMEIRO),
            new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.SEGUNDO),
            new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.TERCEIRO),
            new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.PRIMEIRO),
            new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.SEGUNDO),
            new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.TERCEIRO)
    );

    private final List<Professor> professores;
    private final List<Disciplina> disciplinas;
    private final List<DemandaPPC> demandas;
    private final List<InteresseAlunos> interesses;

    private DefaultTableModel modeloProfessores;
    private DefaultTableModel modeloDisciplinas;
    private DefaultTableModel modeloGrade;
    private DefaultTableModel modeloConflitos;

    public GradeGUI(List<Professor> professores, List<Disciplina> disciplinas,
                    List<DemandaPPC> demandas, List<InteresseAlunos> interesses) {
        this.professores = professores;
        this.disciplinas = disciplinas;
        this.demandas = demandas;
        this.interesses = interesses;

        setTitle("Campus Match — Apoio à Criação de Grades de Horário");
        setSize(880, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(FUNDO);

        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(FUNDO_PAINEL);
        abas.setForeground(TEXTO);
        abas.addTab("Professores", criarPainelProfessores());
        abas.addTab("Disciplinas", criarPainelDisciplinas());
        abas.addTab("Gerar Grade", criarPainelGrade());

        add(abas);
    }

    // ---------- ABA PROFESSORES ----------

    private JPanel criarPainelProfessores() {
        modeloProfessores = new DefaultTableModel(
                new String[]{"Nome", "Matrícula", "Competências", "Carga Horária"}, 0);
        atualizarTabelaProfessores();

        JTable tabela = criarTabelaEstilizada(modeloProfessores);

        JPanel formulario = new JPanel(new GridBagLayout());
        estilizarPainel(formulario, "Adicionar Professor");
        GridBagConstraints c = novasConstraints();

        JTextField campoNome = criarCampoTexto();
        JTextField campoMatricula = criarCampoTexto();
        JSpinner campoCarga = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));

        JPanel painelCompetencias = new JPanel(new GridLayout(0, 2));
        painelCompetencias.setBackground(FUNDO_PAINEL);
        List<JCheckBox> checksCompetencia = criarChecksCompetencia(painelCompetencias);

        JPanel painelHorarios = new JPanel(new GridLayout(0, 2));
        painelHorarios.setBackground(FUNDO_PAINEL);
        List<JCheckBox> checksHorario = criarChecksHorario(painelHorarios);

        int linha = 0;
        adicionarCampo(formulario, c, linha++, "Nome:", campoNome);
        adicionarCampo(formulario, c, linha++, "Matrícula:", campoMatricula);
        adicionarCampo(formulario, c, linha++, "Carga Horária:", campoCarga);
        adicionarCampo(formulario, c, linha++, "Competências:", painelCompetencias);
        adicionarCampo(formulario, c, linha++, "Disponibilidade:", painelHorarios);

        JButton botaoAdicionar = criarBotao("Adicionar Professor");
        botaoAdicionar.addActionListener(e -> {
            try {
                Set<AreaCompetencia> competencias = coletarSelecionadasCompetencia(checksCompetencia);
                Set<Horario> disponibilidade = coletarSelecionadasHorario(checksHorario);
                int cargaHoraria = (int) campoCarga.getValue();

                Professor novo = new Professor(campoNome.getText(), campoMatricula.getText(),
                        competencias, disponibilidade, cargaHoraria);
                professores.add(novo);
                atualizarTabelaProfessores();

                campoNome.setText("");
                campoMatricula.setText("");
                checksCompetencia.forEach(chk -> chk.setSelected(false));
                checksHorario.forEach(chk -> chk.setSelected(false));
            } catch (IllegalArgumentException ex) {
                mostrarErro(ex.getMessage());
            }
        });
        c.gridx = 0; c.gridy = linha; c.gridwidth = 2;
        formulario.add(botaoAdicionar, c);

        return montarPainelComTabelaEFormulario(tabela, formulario);
    }

    private void atualizarTabelaProfessores() {
        modeloProfessores.setRowCount(0);
        for (Professor p : professores) {
            modeloProfessores.addRow(new Object[]{
                    p.getNome(), p.getMatricula(),
                    formatarCompetencias(p.getCompetencias()), p.getCargaHorariaDisponivel()
            });
        }
    }

    // ---------- ABA DISCIPLINAS ----------

    private JPanel criarPainelDisciplinas() {
        modeloDisciplinas = new DefaultTableModel(
                new String[]{"Código", "Nome", "Carga Horária", "Competências Exigidas"}, 0);
        atualizarTabelaDisciplinas();

        JTable tabela = criarTabelaEstilizada(modeloDisciplinas);

        JPanel formulario = new JPanel(new GridBagLayout());
        estilizarPainel(formulario, "Adicionar Disciplina");
        GridBagConstraints c = novasConstraints();

        JTextField campoCodigo = criarCampoTexto();
        JTextField campoNome = criarCampoTexto();
        JSpinner campoCarga = new JSpinner(new SpinnerNumberModel(60, 0, 200, 10));

        JPanel painelCompetencias = new JPanel(new GridLayout(0, 2));
        painelCompetencias.setBackground(FUNDO_PAINEL);
        List<JCheckBox> checksCompetencia = criarChecksCompetencia(painelCompetencias);

        int linha = 0;
        adicionarCampo(formulario, c, linha++, "Código:", campoCodigo);
        adicionarCampo(formulario, c, linha++, "Nome:", campoNome);
        adicionarCampo(formulario, c, linha++, "Carga Horária:", campoCarga);
        adicionarCampo(formulario, c, linha++, "Competências Exigidas:", painelCompetencias);

        JButton botaoAdicionar = criarBotao("Adicionar Disciplina");
        botaoAdicionar.addActionListener(e -> {
            try {
                Set<AreaCompetencia> exigidas = coletarSelecionadasCompetencia(checksCompetencia);
                int cargaHoraria = (int) campoCarga.getValue();

                Disciplina nova = new Disciplina(campoCodigo.getText(), campoNome.getText(),
                        cargaHoraria, exigidas);
                disciplinas.add(nova);
                atualizarTabelaDisciplinas();

                campoCodigo.setText("");
                campoNome.setText("");
                checksCompetencia.forEach(chk -> chk.setSelected(false));
            } catch (IllegalArgumentException ex) {
                mostrarErro(ex.getMessage());
            }
        });
        c.gridx = 0; c.gridy = linha; c.gridwidth = 2;
        formulario.add(botaoAdicionar, c);

        return montarPainelComTabelaEFormulario(tabela, formulario);
    }

    private void atualizarTabelaDisciplinas() {
        modeloDisciplinas.setRowCount(0);
        for (Disciplina d : disciplinas) {
            modeloDisciplinas.addRow(new Object[]{
                    d.getCodigo(), d.getNome(), d.getCargaHoraria(),
                    formatarCompetencias(d.getCompetenciasExigidas())
            });
        }
    }

    // ---------- ABA GERAR GRADE ----------

    private JPanel criarPainelGrade() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(FUNDO);
        painel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JButton botaoGerar = criarBotao("Gerar Sugestão de Grade");
        botaoGerar.addActionListener(e -> executarMatchEAtualizarTela());
        painel.add(botaoGerar, BorderLayout.NORTH);

        modeloGrade = new DefaultTableModel(new String[]{"Disciplina", "Professor", "Horário"}, 0);
        JTable tabelaGrade = criarTabelaEstilizada(modeloGrade);

        modeloConflitos = new DefaultTableModel(new String[]{"Disciplina Não Alocada", "Horário Solicitado"}, 0);
        JTable tabelaConflitos = criarTabelaEstilizada(modeloConflitos);
        tabelaConflitos.setForeground(VERMELHO_ALERTA);

        JSplitPane divisao = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                criarBlocoComTitulo("Grade Gerada", tabelaGrade),
                criarBlocoComTitulo("Disciplinas Não Alocadas / Conflitos", tabelaConflitos));
        divisao.setResizeWeight(0.6);
        divisao.setBackground(FUNDO);

        painel.add(divisao, BorderLayout.CENTER);
        return painel;
    }

    private void executarMatchEAtualizarTela() {
        Match match = new Match(professores, demandas, interesses, new PontuacaoPadraoStrategy());
        match.executarMatch();

        modeloGrade.setRowCount(0);
        for (Alocacao alocacao : match.getGradeCurricular().getAlocacoes()) {
            modeloGrade.addRow(new Object[]{
                    alocacao.getDisciplina().getNome(), alocacao.getProfessor().getNome(), alocacao.getHorario()
            });
        }

        modeloConflitos.setRowCount(0);
        for (DemandaPPC pendente : match.getDemandasNaoAtendidas()) {
            modeloConflitos.addRow(new Object[]{pendente.getDisciplina().getNome(), pendente.getHorario()});
        }

        if (match.getDemandasNaoAtendidas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todas as demandas foram alocadas com sucesso!",
                    "Grade Gerada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    match.getDemandasNaoAtendidas().size() + " disciplina(s) não puderam ser alocadas. Veja a tabela de conflitos.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ---------- HELPERS DE MONTAGEM/ESTILO ----------

    private JPanel montarPainelComTabelaEFormulario(JTable tabela, JPanel formulario) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(FUNDO);
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painel.add(formulario, BorderLayout.SOUTH);
        return painel;
    }

    private JTable criarTabelaEstilizada(DefaultTableModel modelo) {
        JTable tabela = new JTable(modelo);
        tabela.setBackground(FUNDO_PAINEL);
        tabela.setForeground(TEXTO);
        tabela.setGridColor(new Color(45, 51, 59));
        tabela.setRowHeight(24);
        tabela.getTableHeader().setBackground(ROSA);
        tabela.getTableHeader().setForeground(Color.WHITE);
        return tabela;
    }

    private void estilizarPainel(JPanel painel, String titulo) {
        painel.setBackground(FUNDO_PAINEL);
        javax.swing.border.TitledBorder borda = BorderFactory.createTitledBorder(titulo);
        borda.setTitleColor(ROXO);
        painel.setBorder(borda);
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(18);
        campo.setBackground(FUNDO);
        campo.setForeground(TEXTO);
        campo.setCaretColor(TEXTO);
        return campo;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(ROXO);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }

    private GridBagConstraints novasConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    private void adicionarCampo(JPanel formulario, GridBagConstraints c, int linha, String rotulo, JComponent campo) {
        JLabel label = new JLabel(rotulo);
        label.setForeground(TEXTO);
        c.gridx = 0; c.gridy = linha; c.gridwidth = 1;
        formulario.add(label, c);
        c.gridx = 1;
        formulario.add(campo, c);
    }

    private JPanel criarBlocoComTitulo(String titulo, JTable tabela) {
        JPanel bloco = new JPanel(new BorderLayout());
        bloco.setBackground(FUNDO);
        javax.swing.border.TitledBorder borda = BorderFactory.createTitledBorder(titulo);
        borda.setTitleColor(ROXO);
        bloco.setBorder(borda);
        bloco.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return bloco;
    }

    private List<JCheckBox> criarChecksCompetencia(JPanel container) {
        List<JCheckBox> checks = new java.util.ArrayList<>();
        for (AreaCompetencia area : AreaCompetencia.values()) {
            JCheckBox chk = new JCheckBox(area.getDescricaoAmigavel());
            chk.setBackground(FUNDO_PAINEL);
            chk.setForeground(TEXTO);
            checks.add(chk);
            container.add(chk);
        }
        return checks;
    }

    private List<JCheckBox> criarChecksHorario(JPanel container) {
        List<JCheckBox> checks = new java.util.ArrayList<>();
        for (Horario horario : HORARIOS_DISPONIVEIS) {
            JCheckBox chk = new JCheckBox(horario.toString());
            chk.setBackground(FUNDO_PAINEL);
            chk.setForeground(TEXTO);
            checks.add(chk);
            container.add(chk);
        }
        return checks;
    }

    private Set<AreaCompetencia> coletarSelecionadasCompetencia(List<JCheckBox> checks) {
        Set<AreaCompetencia> selecionadas = new HashSet<>();
        AreaCompetencia[] valores = AreaCompetencia.values();
        for (int i = 0; i < checks.size(); i++) {
            if (checks.get(i).isSelected()) selecionadas.add(valores[i]);
        }
        return selecionadas;
    }

    private Set<Horario> coletarSelecionadasHorario(List<JCheckBox> checks) {
        Set<Horario> selecionados = new HashSet<>();
        for (int i = 0; i < checks.size(); i++) {
            if (checks.get(i).isSelected()) selecionados.add(HORARIOS_DISPONIVEIS.get(i));
        }
        return selecionados;
    }

    private String formatarCompetencias(Set<AreaCompetencia> competencias) {
        return competencias.stream()
                .map(AreaCompetencia::getDescricaoAmigavel)
                .collect(Collectors.joining(", "));
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
    }
}