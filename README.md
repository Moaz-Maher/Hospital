## 1. Start SQL Server Service

### Steps:
1. Open **SQL Server Configuration Manager**.
2. Under **SQL Server Services**, locate the SQL Server service for your instance (e.g., `SQL Server (MSSQLSERVER)` or your custom instance name).
3. If the service is not running, right-click on it and select **Start**.

## 2. Enable TCP/IP Protocol

### Steps:
1. In **SQL Server Configuration Manager**, go to **SQL Server Network Configuration** > **Protocols for [Your Instance Name]**.
2. Right-click on **TCP/IP** and select **Enable**.
3. Set TCP Port 1433:
   - Right-click on **TCP/IP** and select **Properties**.
   - Navigate to the **IP Addresses** tab.
   - Scroll to **IPAll** at the bottom and set the **TCP Port** to **1433**.
4. Restart the SQL Server service:
   - Go back to **SQL Server Services**, right-click the service, and select **Restart**.

## 3. Allow SQL Server in Windows Firewall

### Steps:
1. Open **Windows Defender Firewall**:
   - Search for "Windows Defender Firewall" in the Start menu and open it.
   - Click on **Advanced Settings** on the left panel.
2. Create an **Inbound Rule**:
   - In the left panel, click on **Inbound Rules**.
   - On the right-hand side, click **New Rule**.
   - Select **Port** and click **Next**.
   - Choose **TCP**, and specify **1433** in the "Specific local ports" field. Click **Next**.
   - Select **Allow the connection** and click **Next**.
   - Choose when the rule applies (Domain, Private, Public). It’s typically safe to select all three.
   - Name the rule (e.g., "SQL Server TCP Port 1433") and click **Finish**.
3. Create an **Outbound Rule** (Optional):
   - Repeat the above steps under **Outbound Rules** if needed. However, outbound rules are typically less restrictive.

## 4. Execute Each Part of the SQL Script Separately

### Steps:
1. Open SQL script.
2. Highlight each part of the script.
3. Right-click and select **Execute**.