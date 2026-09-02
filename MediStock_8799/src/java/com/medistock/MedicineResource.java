package com.medistock;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("medicines")
public class MedicineResource {

  

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMedicines() {

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String json = "[";

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/MediStockDB",
                    "root",
                    "root"
            );

            String sql = "SELECT * FROM MEDICINES";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            boolean first = true;

            while (rs.next()) {

                if (!first) {
                    json += ",";
                }

                json += "{"
                        + "\"medicine_id\":" + rs.getInt("MEDICINE_ID") + ","
                        + "\"medicine_name\":\"" + rs.getString("MEDICINE_NAME") + "\","
                        + "\"quantity\":" + rs.getInt("QUANTITY") + ","
                        + "\"expiry_date\":\"" + rs.getDate("EXPIRY_DATE") + "\","
                        + "\"status\":\"" + rs.getString("STATUS") + "\""
                        + "}";

                first = false;
            }

            json += "]";

            return json;

        } catch (Exception e) {

            return "{\"error\":\"" + e.getMessage() + "\"}";

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addMedicine(String content) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {

            JsonReader reader =
                    Json.createReader(new StringReader(content));

            JsonObject jsonObject = reader.readObject();

            int medicine_id =
                    jsonObject.getInt("medicine_id");

            String medicine_name =
                    jsonObject.getString("medicine_name");

            int quantity =
                    jsonObject.getInt("quantity");

            String expiry_date =
                    jsonObject.getString("expiry_date");

            String status =
                    jsonObject.getString("status");

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/MediStockDB",
                    "root",
                    "root"
            );

            String sql = "INSERT INTO MEDICINES "
                    + "(MEDICINE_ID, MEDICINE_NAME, QUANTITY, "
                    + "EXPIRY_DATE, STATUS) "
                    + "VALUES (?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(sql);

            pst.setInt(1, medicine_id);
            pst.setString(2, medicine_name);
            pst.setInt(3, quantity);
            pst.setDate(4, Date.valueOf(expiry_date));
            pst.setString(5, status);

            int result = pst.executeUpdate();

            if (result > 0) {

                return "{\"message\":\"Medicine added successfully\"}";

            } else {

                return "{\"message\":\"Medicine not added\"}";
            }

        } catch (Exception e) {

            return "{\"error\":\"" + e.getMessage() + "\"}";

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


 

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateMedicine(String content) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {

            JsonReader reader =
                    Json.createReader(new StringReader(content));

            JsonObject jsonObject = reader.readObject();

            int medicine_id =
                    jsonObject.getInt("medicine_id");

            String medicine_name =
                    jsonObject.getString("medicine_name");

            int quantity =
                    jsonObject.getInt("quantity");

            String expiry_date =
                    jsonObject.getString("expiry_date");

            String status =
                    jsonObject.getString("status");

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/MediStockDB",
                    "root",
                    "root"
            );

            String sql = "UPDATE MEDICINES SET "
                    + "MEDICINE_NAME=?, "
                    + "QUANTITY=?, "
                    + "EXPIRY_DATE=?, "
                    + "STATUS=? "
                    + "WHERE MEDICINE_ID=?";

            pst = conn.prepareStatement(sql);

            pst.setString(1, medicine_name);
            pst.setInt(2, quantity);
            pst.setDate(3, Date.valueOf(expiry_date));
            pst.setString(4, status);
            pst.setInt(5, medicine_id);

            int result = pst.executeUpdate();

            if (result > 0) {

                return "{\"message\":\"Medicine updated successfully\"}";

            } else {

                return "{\"message\":\"Medicine not found\"}";
            }

        } catch (Exception e) {

            return "{\"error\":\"" + e.getMessage() + "\"}";

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    

    @PATCH
    @Path("stock")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStock(String content) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {

            JsonReader reader =
                    Json.createReader(new StringReader(content));

            JsonObject jsonObject = reader.readObject();

            int medicine_id =
                    jsonObject.getInt("medicine_id");

            int quantity =
                    jsonObject.getInt("quantity");

            String status =
                    jsonObject.getString("status");

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/MediStockDB",
                    "root",
                    "root"
            );

            String sql = "UPDATE MEDICINES SET "
                    + "QUANTITY=?, STATUS=? "
                    + "WHERE MEDICINE_ID=?";

            pst = conn.prepareStatement(sql);

            pst.setInt(1, quantity);
            pst.setString(2, status);
            pst.setInt(3, medicine_id);

            int result = pst.executeUpdate();

            if (result > 0) {

                return "{\"message\":\"Medicine stock updated successfully\"}";

            } else {

                return "{\"message\":\"Medicine not found\"}";
            }

        } catch (Exception e) {

            return "{\"error\":\"" + e.getMessage() + "\"}";

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteMedicine(
            @PathParam("id") int medicine_id) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/MediStockDB",
                    "root",
                    "root"
            );

            String sql =
                    "DELETE FROM MEDICINES WHERE MEDICINE_ID=?";

            pst = conn.prepareStatement(sql);

            pst.setInt(1, medicine_id);

            int result = pst.executeUpdate();

            if (result > 0) {

                return "{\"message\":\"Medicine deleted successfully\"}";

            } else {

                return "{\"message\":\"Medicine not found\"}";
            }

        } catch (Exception e) {

            return "{\"error\":\"" + e.getMessage() + "\"}";

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}