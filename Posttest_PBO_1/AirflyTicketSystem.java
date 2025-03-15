import java.util.ArrayList;
import java.util.Scanner;

// Class Pengguna
class Pengguna {
    String userID;
    String nama;
    boolean isAdmin;

    public Pengguna(String userID, String nama, boolean isAdmin) {
        this.userID = userID;
        this.nama = nama;
        this.isAdmin = isAdmin;
    }
}

// Class Pembayaran
class Pembayaran {
    String reservasiID;
    double jumlah;
    boolean status;

    public Pembayaran(String reservasiID, double jumlah) {
        this.reservasiID = reservasiID;
        this.jumlah = jumlah;
        this.status = false;
    }

    public void konfirmasiPembayaran() {
        this.status = true;
    }
}

// Class Penerbangan
class Penerbangan {
    String flightID, asal, tujuan, tanggal, maskapai;
    double harga;

    public Penerbangan(String flightID, String asal, String tujuan, String tanggal, String maskapai, double harga) {
        this.flightID = flightID;
        this.asal = asal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.maskapai = maskapai;
        this.harga = harga;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-10s | %-10s | %-12s | %-10s | Rp%-10.2f |", flightID, asal, tujuan, tanggal, maskapai, harga);
    }
}

// Class Reservasi
class Reservasi {
    String reservasiID, flightID, namaPenumpang;
    boolean statusPembayaran;

    public Reservasi(String reservasiID, String flightID, String namaPenumpang) {
        this.reservasiID = reservasiID;
        this.flightID = flightID;
        this.namaPenumpang = namaPenumpang;
        this.statusPembayaran = false;
    }

    public void setStatusPembayaran(boolean status) {
        this.statusPembayaran = status;
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-10s | %-15s | %-10s |", reservasiID, flightID, namaPenumpang, (statusPembayaran ? "Lunas" : "Pending"));
    }
}

// Sistem Pemesanan
class SistemPemesanan {
    ArrayList<Penerbangan> penerbanganList = new ArrayList<>();
    ArrayList<Reservasi> reservasiList = new ArrayList<>();
    ArrayList<Pembayaran> pembayaranList = new ArrayList<>();

    public void tambahPenerbangan(String flightID, String asal, String tujuan, String tanggal, String maskapai, double harga) {
        penerbanganList.add(new Penerbangan(flightID, asal, tujuan, tanggal, maskapai, harga));
        System.out.println("\nPenerbangan berhasil ditambahkan!");
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

    public void buatReservasi(String reservasiID, String flightID, String namaPenumpang, double harga) {
        for (Penerbangan p : penerbanganList) {
            if (p.flightID.equals(flightID)) {
                reservasiList.add(new Reservasi(reservasiID, flightID, namaPenumpang));
                pembayaranList.add(new Pembayaran(reservasiID, harga));
                System.out.println("\nReservasi berhasil dibuat! Silakan lakukan pembayaran.");
                return;
            }
        }
        System.out.println("\nPenerbangan tidak ditemukan!");
    }

    public void verifikasiPembayaran(String reservasiID) {
        for (Pembayaran p : pembayaranList) {
            if (p.reservasiID.equals(reservasiID)) {
                p.konfirmasiPembayaran();
                for (Reservasi r : reservasiList) {
                    if (r.reservasiID.equals(reservasiID)) {
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
public class AirflyTicketSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemPemesanan sistem = new SistemPemesanan();
        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println(" Airfly Ticket Booking System ");
            System.out.println("=================================");
            System.out.println("1. Tambah Penerbangan (Admin)");
            System.out.println("2. Lihat Penerbangan");
            System.out.println("3. Buat Reservasi");
            System.out.println("4. Lihat Reservasi");
            System.out.println("5. Verifikasi Pembayaran");
            System.out.println("6. Keluar");
            System.out.print("Masukkan pilihan: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Flight ID: ");
                    String fid = scanner.nextLine();
                    System.out.print("Masukkan Asal: ");
                    String asal = scanner.nextLine();
                    System.out.print("Masukkan Tujuan: ");
                    String tujuan = scanner.nextLine();
                    System.out.print("Masukkan Tanggal: ");
                    String tanggal = scanner.nextLine();
                    System.out.print("Masukkan Maskapai: ");
                    String maskapai = scanner.nextLine();
                    System.out.print("Masukkan Harga: ");
                    double harga = scanner.nextDouble();
                    scanner.nextLine();
                    sistem.tambahPenerbangan(fid, asal, tujuan, tanggal, maskapai, harga);
                    break;
                case 2:
                    sistem.lihatPenerbangan();
                    break;
                    case 3:
                    System.out.print("Masukkan Reservasi ID: ");
                    String reservasiID = scanner.nextLine();
                    System.out.print("Masukkan Flight ID: ");
                    String flightID = scanner.nextLine();
                    System.out.print("Masukkan Nama Penumpang: ");
                    String namaPenumpang = scanner.nextLine();
                    System.out.print("Masukkan Harga: ");
                    double hargaReservasi = scanner.nextDouble();
                    scanner.nextLine();
                    sistem.buatReservasi(reservasiID, flightID, namaPenumpang, hargaReservasi);
                    break;
                case 4:
                    sistem.lihatReservasi();
                    break;   
                case 5:
                    System.out.print("Masukkan Reservasi ID untuk verifikasi: ");
                    String verifikasiID = scanner.nextLine();
                    sistem.verifikasiPembayaran(verifikasiID);
                    break;
                case 6:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (choice != 6);
        scanner.close();
    }
}
