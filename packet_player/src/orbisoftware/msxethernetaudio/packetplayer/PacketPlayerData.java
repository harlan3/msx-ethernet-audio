/*
 *  MSX Ethernet Audio
 *
 *  Copyright (C) 2012 Harlan Murphy
 *  Orbis Software - orbisoftware@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package orbisoftware.msxethernetaudio.packetplayer;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class PacketPlayerData {

   private static PacketPlayerData instance = null;
   private String ipAddress = null;
   private Boolean playerActive = false;
   private int currentPacketNumber = 0;
   private int port = 0;

   protected PacketPlayerData() {
      findBroadcastAddress();
      port = 6502;
   }

   public static PacketPlayerData getInstance() {
      if (instance == null) {
         instance = new PacketPlayerData();
      }
      return instance;
   }

   public void setPlayerActive(Boolean newValue) {
      playerActive = newValue;
   }

   public Boolean getPlayerActive() {
      return playerActive;
   }

   public void setIPAddress(String newIPAddress) {
      ipAddress = newIPAddress;
   }

   public String getIPAddress() {
      return ipAddress;
   }

   public void setPort(int newPort) {
      port = newPort;
   }

   public int getPort() {
      return port;
   }

   public void setCurrentPacketNumber(int currentPacketNumber) {
      this.currentPacketNumber = currentPacketNumber;
   }

   public int getCurrentPacketNumber() {
      return currentPacketNumber;
   }

   private void findBroadcastAddress() {

      Enumeration<NetworkInterface> interfaces;
      InetAddress broadcast = null;
      boolean foundAddress = false;

      try {
         interfaces = NetworkInterface.getNetworkInterfaces();

         while (interfaces.hasMoreElements() && foundAddress == false) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback())
               continue;
            for (InterfaceAddress interfaceAddress : networkInterface
                  .getInterfaceAddresses()) {
               broadcast = interfaceAddress.getBroadcast();
               if (broadcast == null)
                  continue;
               else
                  foundAddress = true;
            }
         }
      } catch (SocketException e) {
      }

      if (broadcast != null)
         ipAddress = broadcast.getHostAddress();
      else
         ipAddress = "127.0.0.1";
   }
}
