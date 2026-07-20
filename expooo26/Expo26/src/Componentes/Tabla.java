package Componentes;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class Tabla extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    // Paleta de colores exacta
    private static final Color COLOR_FONDO_TABLA = new Color(15, 10, 20);
    private static final Color COLOR_TEXTO_HEADER = Color.WHITE;
    private static final Color COLOR_TEXTO_CELDA = new Color(220, 220, 225);
    private static final Color COLOR_BORDE_NEON = new Color(60, 30, 80);

    // Colores del degradado neón para la fila seleccionada (Fucsia encendido a Púrpura oscuro)
    private static final Color SELECCION_FUCSIA = new Color(155, 12, 92);
    private static final Color SELECCION_PURPURA_OSCURO = new Color(53, 10, 68);

    // --- COLOR ÚNICO DE REJILLA NEÓN (Morado Eléctrico) ---
    private static final Color GRID_MORADO_NEON = new Color(130, 35, 200);

    // Constructor por defecto (Obligatorio para que NetBeans lo cargue en la Paleta sin errores)
    public Tabla() {
        this(new String[]{"Columna 1", "Columna 2"}); // Columnas temporales para el modo diseño
    }

    // Constructor dinámico definitivo para tus CRUDs
    public Tabla(String[] columnas) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        inicializarTabla(columnas);
    }

    /**
     * Permite reconfigurar o limpiar las columnas de la tabla desde el código
     * del CRUD de forma dinámica
     */
    public void inicializarTabla(String[] columnas) {
        // Removemos componentes previos si se está reinicializando
        removeAll();

        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // --- CREACIÓN DE LA JTABLE PERSONALIZADA ---
        tabla = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                }

                if (isRowSelected(row)) {
                    c.setForeground(Color.WHITE);
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(COLOR_TEXTO_CELDA);
                    c.setFont(c.getFont().deriveFont(Font.PLAIN));
                }
                return c;
            }

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());

                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    Rectangle rectFirst = getCellRect(selectedRow, 0, true);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    GradientPaint gp = new GradientPaint(
                            0, rectFirst.y, SELECCION_FUCSIA,
                            getWidth(), rectFirst.y, SELECCION_PURPURA_OSCURO
                    );
                    g2.setPaint(gp);
                    g2.fillRoundRect(4, rectFirst.y + 3, getWidth() - 8, rectFirst.height - 6, 16, 16);
                    g2.dispose();
                }

                Graphics2D g2Lines = (Graphics2D) g.create();
                g2Lines.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int rows = getRowCount();
                int cols = getColumnCount();
                int tableWidth = getWidth();
                int tableHeight = getHeight();

                float[] puntosDegradado = {0.0f, 0.15f, 0.85f, 1.0f};
                Color[] coloresDegradado = {new Color(0, 0, 0, 0), GRID_MORADO_NEON, GRID_MORADO_NEON, new Color(0, 0, 0, 0)};

                // --- LINEAS HORIZONTALES ---
                g2Lines.setStroke(new BasicStroke(1.2f));
                for (int i = 0; i < rows; i++) {
                    Rectangle r = getCellRect(i, 0, true);
                    int y = r.y + r.height - 1;
                    LinearGradientPaint lgpHorizontal = new LinearGradientPaint(0, y, tableWidth, y, puntosDegradado, coloresDegradado);
                    g2Lines.setPaint(lgpHorizontal);
                    g2Lines.drawLine(4, y, tableWidth - 4, y);
                }

                // --- LÍNEAS VERTICALES ---
                int currentX = 0;
                for (int j = 0; j < cols - 1; j++) {
                    currentX += getColumnModel().getColumn(j).getWidth();
                    LinearGradientPaint lgpVertical = new LinearGradientPaint(currentX, 0, currentX, tableHeight, puntosDegradado, coloresDegradado);
                    g2Lines.setPaint(lgpVertical);
                    g2Lines.drawLine(currentX, 4, currentX, tableHeight - 4);
                }
                g2Lines.dispose();

                super.paintComponent(g);
            }
        };

        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(42);
        tabla.setBackground(COLOR_FONDO_TABLA);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setOpaque(false);
        tabla.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setOpaque(false);
                return this;
            }
        };
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        header.setDefaultRenderer(new CustomHeaderRenderer());

        JScrollPane scroll = new JScrollPane(tabla) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_FONDO_TABLA);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(COLOR_BORDE_NEON);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scroll.getViewport().setBackground(COLOR_FONDO_TABLA);

        // Forzar a que muestre barras automáticas tanto verticales como horizontales
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setRowSelectionInterval(int inicio, int fin) {
        tabla.setRowSelectionInterval(inicio, fin);
    }

    public void requestFocusTabla() {
        tabla.requestFocusInWindow();
    }

    public ListSelectionModel getSelectionModel() {
        return tabla.getSelectionModel();
    }

    public Object getValueAt(int fila, int columna) {
        return tabla.getValueAt(fila, columna);
    }

    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }

    private class CustomHeaderRenderer extends DefaultTableCellRenderer {

        public CustomHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setForeground(COLOR_TEXTO_HEADER);
            label.setFont(new Font("Arial", Font.BOLD, 13));

            return new JPanel() {
                {
                    setLayout(new BorderLayout());
                    setOpaque(false);
                    add(label, BorderLayout.CENTER);
                }

                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(COLOR_FONDO_TABLA);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    LinearGradientPaint lgpHeader = new LinearGradientPaint(
                            0, getHeight() - 1, getWidth(), getHeight() - 1,
                            new float[]{0.0f, 0.15f, 0.85f, 1.0f},
                            new Color[]{new Color(0, 0, 0, 0), GRID_MORADO_NEON, GRID_MORADO_NEON, new Color(0, 0, 0, 0)}
                    );
                    g2.setPaint(lgpHeader);
                    g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
        }
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
