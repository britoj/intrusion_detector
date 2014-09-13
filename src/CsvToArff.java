import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.commons.lang3.*;

public class CsvToArff {

    private static final String[] protocols = {"tcp","udp", "icmp"};
    private static final String[] services = {"aol", "auth", "bgp", "courier", "csnet_ns", "ctf", "daytime", "discard", "domain", "domain_u", "echo", "eco_i", "ecr_i", "efs", "exec", "finger", "ftp", "ftp_data", "gopher", "harvest", "hostnames", "http", "http_2784", "http_443", "http_8001", "imap4", "IRC", "iso_tsap", "klogin", "kshell", "ldap", "link", "login", "mtp", "name", "netbios_dgm", "netbios_ns", "netbios_ssn", "netstat", "nnsp", "nntp", "ntp_u", "other", "pm_dump", "pop_2", "pop_3", "printer", "private", "red_i", "remote_job", "rje", "shell", "smtp", "sql_net", "ssh", "sunrpc", "supdup", "systat", "telnet", "tftp_u", "tim_i", "time", "urh_i", "urp_i", "uucp", "uucp_path", "vmnet", "whois", "X11", "Z39_50"};
    private static final String[] flags = { "OTH", "REJ", "RSTO", "RSTOS0", "RSTR", "S0", "S1", "S2", "S3", "SF", "SH" };

    private static String header = "@relation 'KDD Cup 99'\n" +
    "@attribute 'duration' real\n" +
    "@attribute 'protocol_type' real\n" +
    "@attribute 'service' real\n" +
    "@attribute 'flag' real\n" +
    "@attribute 'src_bytes' real\n" +
    "@attribute 'dst_bytes' real\n" +
    "@attribute 'land' {'0', '1'}\n" +
    "@attribute 'wrong_fragment' real\n" +
    "@attribute 'urgent' real\n" +
    "@attribute 'hot' real\n" +
    "@attribute 'num_failed_logins' real\n" +
    "@attribute 'logged_in' {'0', '1'}\n" +
    "@attribute 'num_compromised' real\n" +
    "@attribute 'root_shell' real\n" +
    "@attribute 'su_attempted' real\n" +
    "@attribute 'num_root' real\n" +
    "@attribute 'num_file_creations' real\n" +
    "@attribute 'num_shells' real\n" +
    "@attribute 'num_access_files' real\n" +
    "@attribute 'num_outbound_cmds' real\n" +
    "@attribute 'is_host_login' {'0', '1'}\n" +
    "@attribute 'is_guest_login' {'0', '1'}\n" +
    "@attribute 'count' real\n" +
    "@attribute 'srv_count' real\n" +
    "@attribute 'serror_rate' real\n" +
    "@attribute 'srv_serror_rate' real\n" +
    "@attribute 'rerror_rate' real\n" +
    "@attribute 'srv_rerror_rate' real\n" +
    "@attribute 'same_srv_rate' real\n" +
    "@attribute 'diff_srv_rate' real\n" +
    "@attribute 'srv_diff_host_rate' real\n" +
    "@attribute 'dst_host_count' real\n" +
    "@attribute 'dst_host_srv_count' real\n" +
    "@attribute 'dst_host_same_srv_rate' real\n" +
    "@attribute 'dst_host_diff_srv_rate' real\n" +
    "@attribute 'dst_host_same_src_port_rate' real\n" +
    "@attribute 'dst_host_srv_diff_host_rate' real\n" +
    "@attribute 'dst_host_serror_rate' real\n" +
    "@attribute 'dst_host_srv_serror_rate' real\n" +
    "@attribute 'dst_host_rerror_rate' real\n" +
    "@attribute 'dst_host_srv_rerror_rate' real\n" +
    "@attribute 'class' {'0', '1'}\n" +
    "@data";

    public static void main(String[] args) throws Exception {
        Map<String, String> protocol = new LinkedHashMap<String, String>();
        for (int i = 0; i < protocols.length; i++) {
            protocol.put(protocols[i], "" + (i + 1));
        }

        Map<String, String> service = new LinkedHashMap<String, String>();
        for (int i = 0; i < services.length; i++) {
            service.put(services[i], "" + (i + 1));
        }

        Map<String, String> flag = new LinkedHashMap<String, String>();
        for (int i = 0; i < flags.length; i++) {
            flag.put(flags[i], "" + (i + 1));
        }

        for (String s : new String[] {"/kddcup99/kddcup_teste_ordenado_registros_unicos.csv", "/kddcup99/kdd_treino_0.csv", "/kddcup99/kdd_treino_1.csv", "/kddcup99/kdd_treino_2.csv", "/kddcup99/kdd_treino_3.csv", "/kddcup99/kdd_treino_4.csv"}) {
            File file = new File(s);
            List<String> newLines = new ArrayList<String>();
            List<String> lines = FileUtils.readLines(file);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("0,tcp,icmp")) {
                    continue;
                }
                String[] fields = lines.get(i).split(",");
                fields[1] = protocol.get(fields[1]);
                fields[2] = service.get(fields[2]);
                fields[3] = flag.get(fields[3]);
                fields[fields.length-1] = "normal.".equals(fields[fields.length-1]) ? "0" : "1";
                newLines.add(StringUtils.join(fields, ","));
            }
            newLines.add(0, header);
            FileUtils.writeLines(new File("/kddcup99/" + file.getName() + ".arff"), newLines);
        }
    }
}
