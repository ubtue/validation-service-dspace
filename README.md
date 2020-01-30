# validation-service-dspace
Add-on mit dem der Validierungsdienst in DSpace Version 6 integriert werden kann.


## Installation des Submission Steps in Dspace

1. Den Ordner mit dem Source Code dem Additions Module hinzufügen:

   Kopiere Ordner <de> nach /data/dspace-6.3-src-release/dspace/modules/additions/src/main/java

2. Benötigte Dependencies für den Submission Step zum pom des Additions Module hinzufügen

   Die benötigten Dependencies können der beiliegenden pom.xml entnommen werden (Ab Kommentar zu ValidationService) (stammt aus virtueller Maschine mit DSpace 6.3)

3. Konfigurationsparameter für den Submission Step hinzufügen

   Die local.cfg Datei der Dspace Installation um die Zeilen der beiliegenden local.cfg erweitern und Einstellungen entsprechend anpassen.

4. Die benötigten Message Keys des Submission Steps zu Xmlui hinzufügen

   4.1. Datei messages.xml um den Inhalt der beiliegenden messages.xml erweitern und bei Bedarf entsprechend anpassen.

   4.2. Datei messages_de.xml um den Inhalt der beiliegenden messages_de.xml erweitern und bei Bedarf entsprechend anpassen.

5. Submission Step als letzten Schritt innerhalb eines Submission Processes einfügen

   Schritt aus item-submission.xml in dspace/config/item-submission.xml unterbringen. 

   Es ist wichtig, dass der Validierungsschritt als letzter Schritt der Verarbeitungskette ausgeführt wird.
   Nur so ist sichergestellt, dass der Schritt nicht vom Nutzer umgangen werden kann.


6. Supported locales de und en in dspace.cfg setzen;

   webui.supported.locales = en, de
