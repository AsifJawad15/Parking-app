#  SyncPark – QR‑Driven Smart Parking App

An Android application designed to automate parking garage management using QR code scanning, a connected backend database, and administrative controls.

---

##  Overview
**SyncPark** enables seamless vehicle entry and exit by scanning QR codes linked to car data. Each scan updates the system in real time via a centralized database. Administrators can access and manage vehicle sessions and garage status through a dedicated control interface.

---

##  Key Features

- **QR-Coded Entry and Exit**  
  Users scan their vehicle’s unique QR code when entering or leaving the garage. The system determines whether the scan corresponds to an “IN” or “OUT” event and logs it accordingly.

- **Database Integration**  
  All transaction data (entry/exit timestamps, vehicle identities) are stored in a backend database for audit, reporting, and real-time monitoring.

- **Administrative Control Panel**  
  Admins can oversee the parking system—view ongoing sessions, manage permissions, and resolve discrepancies.

---

##  Benefits

- **Enhanced Efficiency:** Eliminates manual ticketing and reduces wait times through automated scanning.  
  :contentReference[oaicite:0]{index=0}

- **Improved Accuracy & Control:** Real-time database logging ensures precise tracking of vehicle movements and simplifies reconciliation.

- **Scalable & Secure:** The QR-based architecture is inherently scalable and automates authorization based on code validation; similar frameworks are being adopted in smart parking systems across campuses and municipalities.  
  :contentReference[oaicite:1]{index=1}

---

##  Usage Flow

1. **Vehicle Arrival**
   - Driver scans their pre-generated QR code.
   - The app logs an “IN” event with timestamp.

2. **Vehicle Exit**
   - Driver scans the QR code again.
   - The app logs an “OUT” event and calculates duration if needed.

3. **Administration**
   - Admins monitor live parking logs.
   - Admins can manage permissions and resolve discrepancies.

---

##  Getting Started

```bash
# Clone the repository
git clone https://github.com/AsifJawad15/Parking-app.git

# Open project in Android Studio
# Configure database credentials and server endpoint in the app
# Build and run on an Android device or emulator
