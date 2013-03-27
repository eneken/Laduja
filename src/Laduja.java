
// Vajalikud paketid 
import java.awt.Color;     //Annab klassidele v�rvikasutuse
	import java.awt.Component;    // Graafiline kasutajaliides
	import java.awt.GridLayout;    // Paigutab v�rdse suurustega ristk�likutest v�re 
             import java.awt.event.KeyListener;    // Liides klaviatuuri s�ndmuste (klahvivajutused) jaoks
             import java.awt.event.KeyEvent;    /* Klaviatuuri s�ndmused, objekt muutub, kui klaviatuuri s�ndmus toimub*/
             import javax.swing.JButton;    // Nupule tegevuse  rakendamine
	import javax.swing.JFrame;    // Akna kuvamine, sulgemine
	import javax.swing.JOptionPane;    // Dialoogiboks k�simaks kasutaja v��rtust
/* P�hilass	 Laduja (avalik ehk saab mujal kasutada) laieneb akna kuvamist ja rakendab klaviatuuri s�ndmusi*/
 public class Laduja  extends JFrame implements KeyListener { 
              /* Muutujad  ( static-t�hendab, et on funktsiooni v�line ning on lubatud muutujat kasutada  teistes klassides ja muuta seda , funktsioonide tulemused, mis seda kasutavad muutuvad*/
	        int it = 1;
	        
	        static double aeg = 200;
        static int la = 0;
	        static int l = 10;
	        static int s = 16;
	        static int pikkus[] = {5,5};
	        static int kihid = 15;
	        static int dx[] = {0,0};
	        
	        // T�ev��rtusega muutujad 
	        
	        static boolean vajutus = false;
	        static boolean edasi = true;
	        static boolean algus = true;
	        JButton c[][];
	// Programmi  t�itmine       
          public static void main (String[] args){
	                new Laduja();  
	        }
        // Funktsioon Laduja kirjeldus
        public Laduja(){
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Akna sulgemise kirjeldus 
                this.setTitle("Laduja Eneken_Renee");
	                c = new JButton [l][s];   // Loob nupu ilma pealkirjata (massiivina)
               // Tagatausta loomine, mis koosneb nuppudest
                   setLayout(new GridLayout(s,l));   /* Loob �hesuurused ristk�likud nii mitu etteantud rida ja veergu*/
	        for (int y = 0;y<s;y++){
	            for (int x = 0;x<l;x++){

c[x][y] = new JButton(" ");    // Nuppude pealkirjaks saab t�hik
                    c[x][y].setBackground(Color.yellow);  // Tausta v�rvimine
                    add(c[x][y]);   // Lisab nupud
	                    c[x][y].setEnabled(true);  // Lubab nupud (true)
            }  // Ts�kli for l�pp
	    }
	        setFocusable(true);    // Lubab komponenti fokusseerida (true)
	        addKeyListener(this);   // Lisab komponendile  s�ndmuse
	        pack();     //  kutsume v�lja funktsiooni pack
	        setVisible(true);   //Teeb n�htavaks (true)
	        mine();     // kutsume v�lja funktsiooni mine
	        }
	       // Funktsiooni  mine kirjeldus (sellel funkts. tagastus puudub (void)), klotsi liikumine
	        public void mine(){ 
                               // Muutujad
	                int tp = 0;
	                Component temporaryLostComponent = null;
	              
                             do{
	                if (edasi == true){    //Kui muutuja edasi  on t�ene, siis
	                        edasi();    //k�ivitab funktsiooni  edasi
	                } else {      // Kui if tingimus ei vasta t�ele, siis
	                        tagasi();   // k�ivitab funktsiooni tagasi
	                }
                 if (dx[1] == 10-pikkus[1]){    //Tingimus, kui  klotsix[1]  on v�rdne 10-pikkus[1]
	                        edasi = false;  //siis muutuja edasi v�rdub false
	                } else if (dx[1] == 0){  //Uus tingimus  klotsix[1] v�rdub nulliga
	                        edasi = true;  // siis muutuja tagasi v�rdub true
	                }
	                joonistamine();    // K�ivitab funktsiooni joonistamine
	                try {
	                        Thread.sleep((long) aeg);   // Peatab t�itmise,et anda aega
	                } catch (InterruptedException e) {  // Lubab l�imel v�ljuda
	                        
	                        e.printStackTrace();
	                }
	 
	                }while(vajutus == false); //Klots hakkab j�rjest kiiremini liikuma 10nda kihi juures
	                if (kihid>10){
	                        aeg= 150-(it*it*2-it);
	                } else {
	                        aeg = aeg - 2.3;
	                }
	                it++;
	                kihid--;
	                vajutus = false;
	                tp = kontr();
	                pikkus[0] = pikkus[1];
	                pikkus[1] = tp;
	                
	                if (kihid == 14){  // kahjuks seda teadet ei oska kuvada �igel hetkel, ei saanud �iget tingimust paika
                        JOptionPane.showMessageDialog(temporaryLostComponent, "Lao klotsib �ksteise peale space-klahviga");
	                }
	                if (kihid == -1){
	                        JOptionPane.showMessageDialog(temporaryLostComponent, "Oled v�itja!");
	                }
	                if (pikkus[1] <= 0){   
	                        JOptionPane.showMessageDialog(temporaryLostComponent, "M�ngu l�pp! J�udsid ehitada "+(14-kihid)+"kihti!");
	            System.exit(0);
	                }
	                la = dx[1];
	                algus = false;
	                mine(); // K�ivitab funktsiooni mine
	        }
	              // Funktsiooni kontroll kirjeldus
                            public int kontr(){
	                if (algus == true){
	                        return pikkus[1];
	                } else if (la<dx[1]){  // Uus tingimus
	                        if (dx[1]+pikkus[1]-1 <= la+pikkus[0]-1){
	                                return pikkus[1];
	                        } else {
	                                return pikkus[1]-Math.abs((dx[1]+pikkus[1])-(la+pikkus[0]));
	                        }
	                } else if (la>dx[1]){
	                        return pikkus[1]-Math.abs(dx[1]-la);
	                } else {
	                        return pikkus[1];
	                }
	        }
	        public void edasi(){
	                dx[0] = dx[1];
	                dx[1]++;
	        }
	       
	        public void tagasi(){
	                dx[0] = dx[1];
	                dx[1]--;
       }
	       
	        public void joonistamine(){
	                for (int x = 0;x<pikkus[1];x++){
	                        c[x+dx[0]][kihid].setBackground(Color.yellow);
	                       
	                        }
	                for (int x = 0;x<pikkus[1];x++){	                      
	                	c[x+dx[1]][kihid].setBackground(Color.BLUE);
	                        }
	        } 

    // Klaviatuuri  s�ndmused ning t�hikuklahvi seostamine s�ndmusega
	 
	        @Override
	        public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_SPACE){
	                        vajutus = true;
	                        }



	        }
	 
	        @Override
	        public void keyReleased(KeyEvent arg0) {
	                
	               
	        }
	 
	        @Override
	        public void keyTyped(KeyEvent arg0) {
	               
	               
	        }
	}
