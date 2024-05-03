import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int satır, sütun, zorluk, bomba1, bomba2, puan = 0, bombasayacı = 0;
        while (true) {
            System.out.println("Satır sayısını giriniz");
            satır = scn.nextInt();
            System.out.println("Sütun sayısını giriniz");
            sütun = scn.nextInt();
            if (satır > 2 && satır <= 10 && sütun <= 10 && sütun > 2) {
                while (true) {
                    System.out.println("Zorluk seviyesini giriniz.(%20-%80)");
                    zorluk = scn.nextInt();
                    if (zorluk >= 20 && zorluk <= 80) {
                        break;
                    } else {
                        System.err.println("Lütfen %20 ile %80 arası zorluk giriniz");
                    }
                }
                break;
            } else System.err.println("Lütfen ikiden büyük ondan küçük bir sayı giriniz");
        }
        Random rnd = new Random();
        String[][] yıldız = new String[satır][sütun];
        String[][] bomba = new String[satır][sütun];
        for (int i = 0; i < satır; i++) {
            for (int j = 0; j < sütun; j++) {
                yıldız[i][j] = "*";
                bomba[i][j] = "0";
                System.out.print(yıldız[i][j] + " ");
            }
            System.out.println();
        }
        int bombasayısı = (satır * sütun * zorluk) / 100;
        while (bombasayacı < bombasayısı) {
            bomba1 = rnd.nextInt(0, satır);
            bomba2 = rnd.nextInt(0, sütun);
            if (bomba[bomba1][bomba2].equals("1")) continue;
            else {
                bomba[bomba1][bomba2] = "1";
                bombasayacı++;
            }
        }
        while (true) {
            System.out.println("Açılacak satırı seçiniz. 1 - " + satır);
            int ac1 = scn.nextInt() - 1;
            System.out.println("Açılacak sütunu seçiniz 1 - " + sütun);
            int ac2 = scn.nextInt() - 1;
            if (ac1 < satır && ac1 >= 0 && ac2 < sütun && ac2 >= 0) {
                if (yıldız[ac1][ac2].equals("0")) {
                    System.err.println("Bu yeri daha önceden açtınız.Lütfen başka bi yer tuşlayınız");
                } else if (bomba[ac1][ac2].equals("0")) {
                    puan += 5;
                    if (puan == ((satır * sütun) - bombasayısı) * 5) {
                        for (int i = 0; i < satır; i++) {
                            for (int j = 0; j < sütun; j++) {
                                System.out.print(bomba[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.err.println("Oyunu kazandın.Tebrikler!");
                        System.out.println("Puanın : " + puan);
                        break;
                    } else {
                        yıldız[ac1][ac2] = bomba[ac1][ac2];
                        for (int i = 0; i < satır; i++) {
                            for (int j = 0; j < sütun; j++) {
                                System.out.print(yıldız[i][j] + " ");
                            }
                            System.out.println();
                        }

                    }
                } else {
                    for (int i = 0; i < satır; i++) {
                        for (int j = 0; j < sütun; j++) {
                            System.out.print(bomba[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.err.println("Mayın patladı.Oyunu kaybettin.Puanın : " + puan);
                    break;
                }
            } else {
                System.err.println("Lütfen 1 - " + satır + "arası sayı seçiniz");
            }
        }
        scn.close();
    }
}