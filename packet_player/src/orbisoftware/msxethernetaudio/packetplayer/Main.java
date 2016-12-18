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

public class Main {

   public static void main(String[] args) {

      SendDatagramThread sendDatagramThread = new SendDatagramThread();
      sendDatagramThread.start();

      // Schedule a job for the event-dispatching thread to
      // create and show this application's GUI.

      javax.swing.SwingUtilities.invokeLater(new Runnable() {

         public void run() {
            PacketPlayerUI packetPlayerUI = PacketPlayerUI.getInstance();

            packetPlayerUI.createAndShowGUI();
         }
      });
   }
}
