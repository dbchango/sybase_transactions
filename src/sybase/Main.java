package sybase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
    	Tool util_tool = new Tool();
    	String resourceName = "myconf.properties";
    	String sql_process = "";
    	String secuencial_tramite = "";
    	String codigo_ente = "";

    	sql_process = args[0]; // sql flow process option
    	secuencial_tramite = args[1];
  
    	if(args.length == 3) {
    		codigo_ente = args[2];
    	}
    	
    	String sql_query = "";
    	
    	Properties props = util_tool.readConfigFile(resourceName);

    	String connection_string = "jdbc:" + props.get("database.type")
    				+ ":user=" + props.get("database.user")
    				+ ";password=" + props.get("database.password")
    				+ ";Server=" + props.get("database.server")
    				+ ";Port=" + props.get("database.port");
    	
    	Class.forName("cdata.jdbc.sybase.SybaseDriver");
        Connection connection = DriverManager.getConnection(connection_string);
        Statement stat = connection.createStatement();
        if ("score_fna".matches(sql_process)) {
    		sql_query = "update cob_credito..cr_deudores set de_cif = 'SCOREFNAHD', de_score_cif = 700, de_fecha_cif = '05/26/2023' where de_tramite = " + secuencial_tramite + ";";
        }else if ("capacidad_pago_preliminar".matches(sql_process )) {
    		sql_query = "insert into cob_credito..cr_capacidad_pago_preeliminar select " + secuencial_tramite + ", cp_tipo_solicitud, cp_truta, cp_etapa, cp_rol, " + codigo_ente + ", cp_nombres, cp_numero_identif, cp_tipo_identif, cp_ingresos, cp_otros_ingresos, cp_total_ingresos, cp_egresos_financieros, cp_egresos_manutencion, cp_total_egresos,  cp_total_capacidad, cp_viabilidad,     cp_vigencia_viabilidad,  cp_fecha_registro, cp_usuario, cp_terminal, cp_oficina from cob_credito..cr_capacidad_pago_preeliminar where cp_tramite = 1679666;";
    	}else if ("insertar_datos_sabana_comercial".matches(sql_process)) {
    		sql_query = "insert into cob_credito..cr_sabana_comercial(sc_concepto, sc_tramite, sc_ente, sc_rol, sc_truta, sc_tipo_entidad, sc_nom_entidad, sc_num_obligacion, sc_estado, sc_cupo, sc_mcr, sc_lcr, sc_vr_inicial,  sc_saldo, sc_vr_calcula_per, sc_ult_comporta, sc_periodicidad, sc_calidad, sc_fecha_corte, sc_vr_cuota, sc_vr_cuota_calc1, sc_vr_cuota_calc2, sc_reestructurado, sc_saldo_calc, sc_cupo_calc1, sc_cupo_calc2, sc_periodo, sc_observacion, sc_usuario, sc_fecha_proceso, sc_valor_mora, sc_vec_comporta, sc_fecha_vencimiento) select sc_concepto, "+ secuencial_tramite +", " + codigo_ente + ", sc_rol, sc_truta, sc_tipo_entidad, sc_nom_entidad, sc_num_obligacion, sc_estado, sc_cupo, sc_mcr, sc_lcr, sc_vr_inicial,  sc_saldo, sc_vr_calcula_per, sc_ult_comporta,   sc_periodicidad, sc_calidad, sc_fecha_corte, sc_vr_cuota, sc_vr_cuota_calc1, sc_vr_cuota_calc2, sc_reestructurado, sc_saldo_calc, sc_cupo_calc1, sc_cupo_calc2, sc_periodo, sc_observacion, sc_usuario,sc_fecha_proceso, sc_valor_mora, sc_vec_comporta, '12/01/2025' from cob_credito..cr_sabana_comercial where sc_tramite = 1679666";
    	}
        
        Integer ret = stat.executeUpdate(sql_query);
        
        //boolean ret = stat.execute(sql_query);
        
        if (ret == 1) {
        	System.out.println("Transacción exitosa");
        } else {
        	System.out.println("Hubo un problema al ejecutar la transacción");
        }
    }
}
