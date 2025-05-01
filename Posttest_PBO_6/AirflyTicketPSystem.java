import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.InputMismatchException;

// Abstract class sebagai parent
abstract class Pengguna {
    protected String userID;
    protected String nama;

    public Pengguna(String userID, String nama) {
        this.userID = userID;
        this.nama = nama;
    }

    public String getUserID() {
        return userID;
    }

    public String getNama() {
        return nama;
    }

    // Abstract method wajib dioverride di subclass
    public abstract void menu(SistemPemesanan sistem, Scanner scanner);
}

// Subclass Admin
class Admin extends Pengguna {
    private final String role = "Admin"; // final variable

    public Admin(String userID, String nama) {
        super(userID, nama);
    }

    @Override
    public void menu(SistemPemesanan sistem, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n1. Tambah Penerbangan");
            System.out.println("2. Hapus Penerbangan");
            System.out.println("3. Lihat Penerbangan");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Flight ID: ");
                        String fid = scanner.nextLine();
                        System.out.print("Asal: ");
                        String asal = scanner.nextLine();
                        System.out.print("Tujuan: ");
                        String tujuan = scanner.nextLine();
                        System.out.print("Tanggal: ");
                        String tanggal = scanner.nextLine();
                        System.out.print("Maskapai: ");
                        String maskapai = scanner.nextLine();
                        System.out.print("Harga: ");
                        double harga = scanner.nextDouble();
                        scanner.nextLine();
                        sistem.tambahPenerbangan(fid, asal, tujuan, tanggal, maskapai, harga);
                        break;
                    case 2:
                        sistem.lihatPenerbangan();
                        System.out.print("Masukkan Flight ID yang ingin dihapus: ");
                        String hapusID = scanner.nextLine();
                        sistem.hapusPenerbangan(hapusID);
                        break;
                    case 3:
                        sistem.lihatPenerbangan();
                        break;
                    case 4:
                        System.out.println("Kembali ke menu utama...");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid! Masukkan angka.");
                scanner.nextLine(); // clear buffer
            }
        } while (true);
    }

    // Final method
    public final void tampilDataAdmin() {
        System.out.println("Admin: " + nama + " | ID: " + userID + " | Role: " + role);
    }
}

// Subclass Penumpang
class Penumpang extends Pengguna {
    public Penumpang(String userID, String nama) {
        super(userID, nama);
    }

    @Override
    public void menu(SistemPemesanan sistem, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n1. Lihat Penerbangan");
            System.out.println("2. Buat Reservasi");
            System.out.println("3. Lihat Reservasi");
            System.out.println("4. Bayar Reservasi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

            switch (choice) {
                case 1:
                    sistem.lihatPenerbangan();
                    break;
                case 2:
                    sistem.lihatPenerbangan();
                    System.out.print("Masukkan Flight ID yang ingin dipesan: ");
                    String flightID = scanner.nextLine();
                    System.out.print("Masukkan Reservasi ID: ");
                    String reservasiID = scanner.nextLine();
                    sistem.buatReservasi(reservasiID, flightID, nama, 0.0);
                    break;
                case 3:
                    sistem.lihatReservasi();
                    break;
                case 4:
                sistem.lihatReservasi();
                System.out.print("Masukkan Reservasi ID yang ingin dibayar: ");
                String bayarID = scanner.nextLine();
                sistem.verifikasiPembayaran(bayarID);
                break;
                case 5:
                    System.out.println("Kembali ke menu utama...");
                    return; // Keluar dari menu
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid! Masukkan angka.");
            scanner.nextLine();
        }
    } while (true);
}
}    

class Flight {
    String id;
    Flight(String id) {
        this.id = id;
    }
}

// Class Pembayaran dengan Encapsulation
class Pembayaran {
    private String reservasiID;
    private double jumlah;
    private boolean status;

    public Pembayaran(String reservasiID, double jumlah) {
        this.reservasiID = reservasiID;
        this.jumlah = jumlah;
        this.status = false;
    }

    // Getter untuk jumlah
    public double getJumlah() {
        return jumlah;
}

    // Metode untuk menampilkan jumlah pembayaran
    public void tampilkanJumlah() {
        System.out.println("Jumlah pembayaran: Rp " + jumlah);
    }

    // Getter untuk status pembayaran
    public boolean isLunas() {
        return status;
    }

    // Metode untuk konfirmasi pembayaran
    public void konfirmasiPembayaran() {
        this.status = true;
    }

    public String getReservasiID() {
        return reservasiID;
    }
}

// Class Penerbangan dengan Encapsulation
class Penerbangan {
    private String flightID, asal, tujuan, tanggal, maskapai;
    private double harga;

    public Penerbangan(String flightID, String asal, String tujuan, String tanggal, String maskapai, double harga) {
        this.flightID = flightID;
        this.asal = asal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.maskapai = maskapai;
        this.harga = harga;
    }

    // Getter untuk Flight ID
    public String getFlightID() {
        return flightID;
    }

    // Getter untuk Harga
    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-10s | %-10s | %-12s | %-10s | Rp%-10.2f |", flightID, asal, tujuan, tanggal, maskapai, harga);
    }
}

// Class Reservasi dengan Encapsulation
class Reservasi {
    private String reservasiID, flightID, namaPenumpang;
    private boolean statusPembayaran;

    public Reservasi(String reservasiID, String flightID, String namaPenumpang) {
        this.reservasiID = reservasiID;
        this.flightID = flightID;
        this.namaPenumpang = namaPenumpang;
        this.statusPembayaran = false;
    }

      // Getter untuk reservasiID
    public String getReservasiID() {
        return reservasiID;
    }

    // Getter untuk status pembayaran
    public boolean isLunas() {
        return statusPembayaran;
    }

    // Setter untuk mengubah status pembayaran
    public void setStatusPembayaran(boolean status) {
        this.statusPembayaran = status;
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-10s | %-15s | %-10s |", reservasiID, flightID, namaPenumpang, (statusPembayaran ? "Lunas" : "Pending"));
    }
}

// Sistem Pemesanan dengan perubahan Encapsulation
class SistemPemesanan {
    private ArrayList<Penerbangan> penerbanganList = new ArrayList<>();
    private ArrayList<Reservasi> reservasiList = new ArrayList<>();
    private ArrayList<Pembayaran> pembayaranList = new ArrayList<>();

    private static int totalReservasi = 0; // <== Tambahan static

    SistemPemesanan() {}

    // Menambahkan metode untuk menampilkan total reservasi
    public void tampilkanTotalReservasi() {
        int total = reservasiList.size(); // Menghitung jumlah reservasi
        System.out.println("Total reservasi: " + total);
    }

    public void tambahPenerbangan(String flightID, String asal, String tujuan, String tanggal, String maskapai, double harga) {
        for (Penerbangan p : penerbanganList) {
            if (p.getFlightID().equals(flightID)) {
                System.out.println("Flight ID sudah ada!");
                return;
            }
        }
        penerbanganList.add(new Penerbangan(flightID, asal, tujuan, tanggal, maskapai, harga));
        System.out.println("Penerbangan berhasil ditambahkan!");
    }

    // Menghapus penerbangan
    public void hapusPenerbangan(String flightID) {
        if (penerbanganList.isEmpty()) {
            System.out.println("\nTidak ada penerbangan yang tersedia.");
            return;
        }

        ListIterator<Penerbangan> iterator = penerbanganList.listIterator();
        while (iterator.hasNext()) {
            Penerbangan p = iterator.next();
            if (p.getFlightID().equals(flightID)) {
                iterator.remove();
                System.out.println("\nPenerbangan " + flightID + " berhasil dihapus.");
                return;
            }
        }
        System.out.println("\nPenerbangan tidak ditemukan!");
    }

    public void lihatPenerbangan() {
        if (penerbanganList.isEmpty()) {
            System.out.println("\nTidak ada penerbangan yang tersedia.");
        } else {
            System.out.println("\n+------------+------------+------------+--------------+------------+------------------+");
            System.out.println("| Flight ID  | Asal       | Tujuan       | Tanggal      | Maskapai   | Harga          |");
            System.out.println("+------------+------------+--------------+--------------+------------+----------------+");
            for (Penerbangan p : penerbanganList) {
                System.out.println(p);
            }
            System.out.println("+------------+------------+------------+--------------+------------+----------------+");
        }
    }

   // Versi utama: dengan harga eksplisit (walau tidak digunakan karena pakai harga dari penerbangan)
public void buatReservasi(String reservasiID, String flightID, String namaPenumpang, double hargaReservasi) {
    if (isReservasiIDExist(reservasiID)) {
        System.out.println("Reservasi ID sudah digunakan!");
        return;
    }

    Penerbangan flight = cariPenerbangan(flightID);
    if (flight != null) {
        reservasiList.add(new Reservasi(reservasiID, flightID, namaPenumpang));
        pembayaranList.add(new Pembayaran(reservasiID, flight.getHarga()));
        System.out.println("Reservasi berhasil dibuat dengan harga Rp " + flight.getHarga());
    } else {
        System.out.println("Penerbangan tidak ditemukan!");
    }
}

    // Overloading 1: tanpa harga, akan otomatis ambil dari harga penerbangan
    public void buatReservasi(String reservasiID, String flightID, String namaPenumpang) {
        buatReservasi(reservasiID, flightID, namaPenumpang, 0); 
    }

    // Overloading 2: tanpa flightID (misal untuk pre-booking atau draft reservasi)
    public void buatReservasi(String reservasiID, String namaPenumpang) {
        if (isReservasiIDExist(reservasiID)) {
            System.out.println("Reservasi ID sudah digunakan!");
            return;
        }
        reservasiList.add(new Reservasi(reservasiID, "Belum-Ditentukan", namaPenumpang));
        pembayaranList.add(new Pembayaran(reservasiID, 0)); 
        System.out.println("Reservasi sementara berhasil dibuat untuk " + namaPenumpang);
    }

    // Fungsi bantu
    private boolean isReservasiIDExist(String reservasiID) {
        for (Reservasi r : reservasiList) {
            if (r.getReservasiID().equals(reservasiID)) {
                return true;
            }
        }
        return false;
    }

    private Penerbangan cariPenerbangan(String flightID) {
        for (Penerbangan p : penerbanganList) {
            if (p.getFlightID().equals(flightID)) {
                return p;
            }
        }
        return null;
    }

    public void verifikasiPembayaran(String reservasiID) {
        for (Pembayaran p : pembayaranList) {
            if (p.getReservasiID().equals(reservasiID)) {
                p.konfirmasiPembayaran();
                for (Reservasi r : reservasiList) {
                    if (r.getReservasiID().equals(reservasiID)) {
                        r.setStatusPembayaran(true);
                        System.out.println("\nPembayaran berhasil diverifikasi!");
                        return;
                    }
                }
            }
        }
        System.out.println("\nReservasi tidak ditemukan atau pembayaran belum dilakukan.");
    }

    public void lihatReservasi() {
        if (reservasiList.isEmpty()) {
            System.out.println("\nTidak ada reservasi.");
        } else {
            System.out.println("\n+--------------+------------+-----------------+------------+");
            System.out.println("| Reservasi ID | Flight ID  | Nama Penumpang  | Status     |");
            System.out.println("+--------------+------------+-----------------+------------+");
            for (Reservasi r : reservasiList) {
                System.out.println(r);
            }
            System.out.println("+--------------+------------+-----------------+------------+");
        }
    }
}


// Main Program
public class AirflyTicketPSystem {

    static Scanner scanner = new Scanner(System.in);
    static SistemPemesanan sistem = new SistemPemesanan();
 
    public static void main(String[] args) {
        SistemPemesanan sistem = new SistemPemesanan();
        boolean berjalan = true;
 
       do {
        System.out.println("Masukkan peran Anda (Admin/Penumpang): ");
        String peran = scanner.nextLine().trim();

        // Validasi input peran
        if (peran.equalsIgnoreCase("Admin")) {
            Admin admin = new Admin("A001", "Admin1");
            admin.menu(sistem, scanner);
        } else if (peran.equalsIgnoreCase("Penumpang")) {
            Penumpang penumpang = new Penumpang("P001", "Nabila");
            penumpang.menu(sistem, scanner);
        } else {
            System.out.println("Peran yang dimasukkan tidak valid. Harap masukkan 'Admin' atau 'Penumpang'.");
            continue; // Mengulang input peran jika tidak valid
        }
 
          while (berjalan) {
             tampilkanMenuUtama();
 
             try {
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Buang newline
 
                berjalan = prosesPilihan(pilihan);
             } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harus berupa angka.");
                scanner.nextLine(); // bersihkan buffer
             }
          }
 
       } while (false);
 
       scanner.close();
    }
 
    public static void tampilkanMenuUtama() {
       System.out.println("\n=================================");
       System.out.println(" Airfly Ticket Booking System ");
       System.out.println("=================================");
       System.out.println("1. Tambah Penerbangan (Admin)");
       System.out.println("2. Lihat Penerbangan");
       System.out.println("3. Buat Reservasi");
       System.out.println("4. Lihat Reservasi");
       System.out.println("5. Verifikasi Pembayaran");
       System.out.println("6. Tampilkan Total Reservasi");
       System.out.println("7. Keluar");
       System.out.print("Masukkan pilihan: ");
    }
 
    public static boolean prosesPilihan(int pilihan) {
       switch (pilihan) {
          case 1:
             System.out.print("Masukkan Flight ID: ");
             String flightID = scanner.nextLine();
             System.out.print("Masukkan Asal: ");
             String asal = scanner.nextLine();
             System.out.print("Masukkan Tujuan: ");
             String tujuan = scanner.nextLine();
             System.out.print("Masukkan Tanggal: ");
             String tanggal = scanner.nextLine();
             System.out.print("Masukkan Maskapai: ");
             String maskapai = scanner.nextLine();
             System.out.print("Masukkan Harga: ");
 
             try {
                double harga = scanner.nextDouble();
                scanner.nextLine();
                sistem.tambahPenerbangan(flightID, asal, tujuan, tanggal, maskapai, harga);
             } catch (InputMismatchException e) {
                System.out.println("Harga harus berupa angka!");
                scanner.nextLine();
             }
             break;
 
          case 2:
             sistem.lihatPenerbangan();
             break;
 
          case 3:
             sistem.lihatPenerbangan();
             System.out.print("Masukkan Reservasi ID: ");
             String resID = scanner.nextLine();
             System.out.print("Masukkan Flight ID: ");
             String flight = scanner.nextLine();
             System.out.print("Masukkan nama penumpang: ");
             String nama = scanner.nextLine();
             System.out.print("Masukkan Harga: ");
 
             try {
                double hargaReservasi = scanner.nextDouble();
                scanner.nextLine();
                sistem.buatReservasi(resID, flight, nama, hargaReservasi);
             } catch (InputMismatchException e) {
                System.out.println("Harga harus berupa angka!");
                scanner.nextLine();
             }
             break;
 
          case 4:
             sistem.lihatReservasi();
             break;
 
          case 5:
             System.out.print("Masukkan Reservasi ID untuk verifikasi: ");
             String resVerifikasi = scanner.nextLine();
             sistem.verifikasiPembayaran(resVerifikasi);
             break;
 
          case 6:
             sistem.tampilkanTotalReservasi(); // static method
             break;
 
          case 7:
             System.out.print("Apakah Anda yakin ingin keluar? (y/n): ");
             String konfirmasi = scanner.nextLine();
             if (konfirmasi.equalsIgnoreCase("y")) {
                System.out.println("Terima kasih telah menggunakan sistem ini.");
                return false;
             }
             break;
 
          default:
             System.out.println("Pilihan tidak valid, coba lagi.");
       }
       return true;
    }
 }

