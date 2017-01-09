import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class SimonDice extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelColor1;
	private JPanel panelColor2;
	private JPanel panelColor3;
	private JPanel panelColor4;

	private JPanel panelColores;

	private int ROJO = 0, AZUL = 1, AMARILLO = 2, VERDE = 3;
	private int[] codigoColores = new int[4];

	private String[] nombreColores = new String[4];

	private ArrayList<Integer> secuenciaNivel = new ArrayList<Integer>();
	private int nivel = 4;
	private ArrayList<Integer> secuenciaJugador = new ArrayList<Integer>();

	private boolean continuar = false;
	int opc = 0;

	// Opciones del juego
	int tiempoEncendido = 500;
	int tiempoDeEsperaEntreColores = 250;

	Color rojo = Color.red;
	Color azul = Color.blue;
	Color amarillo = Color.yellow;
	Color verde = Color.green;

	String nombreColor1 = "";
	String nombreColor2 = "";
	String nombreColor3 = "";
	String nombreColor4 = "";

	public SimonDice() throws Exception {
		iniciarGUI();
	}

	public void iniciarGUI() throws Exception {
		panelColores = new JPanel();

		panelColor1 = new JPanel();
		panelColor2 = new JPanel();
		panelColor3 = new JPanel();
		panelColor4 = new JPanel();

		panelColores.setLayout(new GridLayout(2, 2));

		panelColor1.setBackground(rojo.darker().darker());
		panelColor2.setBackground(azul.darker().darker());
		panelColor3.setBackground(amarillo.darker().darker());
		panelColor4.setBackground(verde.darker().darker());

		panelColor1.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor2.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor3.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor4.setBorder(BorderFactory.createRaisedBevelBorder());

		panelColores.add(panelColor1);
		panelColores.add(panelColor2);
		panelColores.add(panelColor3);
		panelColores.add(panelColor4);

		add(panelColores);

		// Creamos los arrays que usaremos
		codigoColores[0] = ROJO;
		codigoColores[1] = AZUL;
		codigoColores[2] = AMARILLO;
		codigoColores[3] = VERDE;

		nombreColores[0] = "Rojo";
		nombreColores[1] = "Azul";
		nombreColores[2] = "Amarillo";
		nombreColores[3] = "Verde";

		pack();
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void jugar() throws Exception {
		// seleccionarColores();
		try {
			modoDeJuego();

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {

			ejecutarSecuencia(secuenciaNivel);

			activarPaneles();
			JOptionPane.showMessageDialog(this, "Nivel "+ (nivel-3) + "\nTu turno:");

			do {
				try {
					System.out.println(secuenciaJugador);
				} catch (Exception e) {

				}

			} while (secuenciaJugador.size() < nivel);

			comprobarSecuencia(secuenciaJugador);
			secuenciaJugador.removeAll(secuenciaJugador);

			desactivarPaneles();
			coloresPredeterminados();

			// Comprobar si se desea continuar
			opc = 0;
			continuar = false;
			do {
				switch (JOptionPane.showOptionDialog(this, "Desea continuar?", "Opcion", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "0")) {
				case 0:
					continuarSecuencia();
					JOptionPane.showMessageDialog(this, "Ha seleccionado continuar.");
					opc = 1;
					break;

				case 1:
					JOptionPane.showMessageDialog(this, "A continuacion la aplicacion se cerrara.");
					System.exit(0);
					break;

				default:
					JOptionPane.showMessageDialog(this, "Escoja de nuevo.");
					break;
				}
			} while (opc == 0);

		} while (continuar = !false);
	}
	
	// Activar los escuchadores de los paneles o desactivarlos
	public void activarPaneles() {
		panelColor1.addMouseListener(this);
		panelColor2.addMouseListener(this);
		panelColor3.addMouseListener(this);
		panelColor4.addMouseListener(this);
	}

	public void desactivarPaneles() {
		panelColor1.removeMouseListener(this);
		panelColor2.removeMouseListener(this);
		panelColor3.removeMouseListener(this);
		panelColor4.removeMouseListener(this);
	}

	// Escuchador de los eventos click del raton
	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == panelColor1) {
			panelColor1.setBackground(rojo);
			panelColor1.setBorder(BorderFactory.createLoweredBevelBorder());
			secuenciaJugador.add(ROJO);
		}
		if (event.getSource() == panelColor2) {
			panelColor2.setBackground(azul);
			panelColor2.setBorder(BorderFactory.createLoweredBevelBorder());
			secuenciaJugador.add(AZUL);
		}
		if (event.getSource() == panelColor3) {
			panelColor3.setBackground(amarillo);
			panelColor3.setBorder(BorderFactory.createLoweredBevelBorder());
			secuenciaJugador.add(AMARILLO);
		}
		if (event.getSource() == panelColor4) {
			panelColor4.setBackground(verde);
			panelColor4.setBorder(BorderFactory.createLoweredBevelBorder());
			secuenciaJugador.add(VERDE);
		}

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == panelColor1) {
			panelColor1.setBackground(rojo.darker().darker());
			panelColor1.setBorder(BorderFactory.createRaisedBevelBorder());

		}
		if (event.getSource() == panelColor2) {
			panelColor2.setBackground(azul.darker().darker());
			panelColor2.setBorder(BorderFactory.createRaisedBevelBorder());

		}
		if (event.getSource() == panelColor3) {
			panelColor3.setBackground(amarillo.darker().darker());
			panelColor3.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		if (event.getSource() == panelColor4) {
			panelColor4.setBackground(verde.darker().darker());
			panelColor4.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	}

	// Selector del modo de juego (Secuencia aleatoria o Extraida de un txt)
	public void modoDeJuego() throws IOException, HeadlessException {
		opc = 0;
		continuar = false;
		do {
			try {
				switch (JOptionPane.showOptionDialog(this, "Seleccione la secuencia", "Opcion",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Generada aleatoriamente.", "Del fichero de secuencias." }, "0")) {
				case 0:
					JOptionPane.showMessageDialog(this, "Ha seleccionado generar secuencia aleatoria.");
					opc = 1;
					secuenciaNivel = generarSecuencia(nivel);
					break;

				case 1:
					JOptionPane.showMessageDialog(this,
							"A continuacion seleccionaremos la secuencia de un fichero txt.");
					opc = 2;
					secuenciaNivel = SelectorDeSecuencias.seleccionarSecuenciaDeTxt();
					this.nivel = secuenciaNivel.size();
					break;

				default:
					JOptionPane.showMessageDialog(this, "Escoja de nuevo.");
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (opc == 0);

	}

	// Generador de secuencias
	public ArrayList<Integer> generarSecuencia(int nivel) {
		ArrayList<Integer> secuencia = new ArrayList<Integer>();
		int numero = 0;
		double random = 0;

		for (int i = 0; i < nivel; i++) {
			random = Math.random() * 4;
			numero = (int) random;

			secuencia.add(numero);
		}

		return secuencia;
	}

	// Tras acertar la secuencia, añade un digito mas a la secuencia de colores
	public void continuarSecuencia() {
		int numero = 0;
		double random = 0;

		random = Math.random() * 4;
		numero = (int) random;
		secuenciaNivel.add(numero);

	}

	// Compruba que la secuencia introducida por el jugador mediante clicks en los paneles sea la correcta
	public void comprobarSecuencia(ArrayList<Integer> secuenciaJugador) throws Exception {
		boolean secuenciaCorrecta = false, correctoAux = true;

		for (int i = 0; i < secuenciaJugador.size() && correctoAux != false; i++) {

			if (secuenciaJugador.get(i) == secuenciaNivel.get(i)) {

				//La secuencia es correcta

			} else {
				correctoAux = false;
			}
		}
		if (correctoAux) {
			secuenciaCorrecta = true;
		}

		if (secuenciaCorrecta != false) {

			JOptionPane.showMessageDialog(this, "Correcto");
			this.nivel = this.nivel + 1;
		} else {

			JOptionPane.showMessageDialog(this, "Incorrecto");
		}

	}

	// Ejecuta la secuencia del nivel, iluminando la secuencia
	public void ejecutarSecuencia(ArrayList<Integer> secuencia) throws InterruptedException {

		Thread.sleep(1000);

		for (int i = 0; i < nivel; i++) {
			if (secuencia.get(i) == ROJO) {

				Thread.sleep(tiempoDeEsperaEntreColores);
				panelColor1.setBackground(rojo);
				Thread.sleep(tiempoEncendido);
				panelColor1.setBackground(rojo.darker().darker());

			} else if (secuencia.get(i) == AZUL) {
				Thread.sleep(tiempoDeEsperaEntreColores);
				panelColor2.setBackground(azul);
				Thread.sleep(tiempoEncendido);
				panelColor2.setBackground(azul.darker().darker());

			} else if (secuencia.get(i) == AMARILLO) {
				Thread.sleep(tiempoDeEsperaEntreColores);
				panelColor3.setBackground(amarillo);
				Thread.sleep(tiempoEncendido);
				panelColor3.setBackground(amarillo.darker().darker());

			} else if (secuencia.get(i) == VERDE) {
				Thread.sleep(tiempoDeEsperaEntreColores);
				panelColor4.setBackground(verde);
				Thread.sleep(tiempoEncendido);
				panelColor4.setBackground(verde.darker().darker());

			} else {
				JOptionPane.showMessageDialog(this, "Algo ha fallado ejecutando la secuencia.");
			}
		}

	}

	// Devuelve los colores al estado predeterminado (Se ejecuta para que no se quede ningun color encencido)
	public void coloresPredeterminados() {
		panelColor1.setBackground(rojo.darker().darker());
		panelColor2.setBackground(azul.darker().darker());
		panelColor3.setBackground(amarillo.darker().darker());
		panelColor4.setBackground(verde.darker().darker());

		panelColor1.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor2.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor3.setBorder(BorderFactory.createRaisedBevelBorder());
		panelColor4.setBorder(BorderFactory.createRaisedBevelBorder());

	}

	// Metodo main
	public static void main(String[] args) throws Exception {

		SimonDice simonDice = new SimonDice();

		JOptionPane.showMessageDialog(simonDice, "Simon Dice: ");

		try {
			simonDice.jugar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
