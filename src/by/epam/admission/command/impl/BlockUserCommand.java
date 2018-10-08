/*
 * class: BlockUserCommand
 */

package by.epam.admission.command.impl;

import by.epam.admission.command.ActionCommand;
import by.epam.admission.command.Router;
import by.epam.admission.exception.ProjectException;
import by.epam.admission.logic.UserService;
import by.epam.admission.model.User;
import by.epam.admission.util.Names;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Burishinets Maxim
 * @version 1.0 27 Sep 2018
 */
public class BlockUserCommand implements ActionCommand {

    private static final Logger LOG =
            LogManager.getLogger(BlockUserCommand.class);

    @Override
    public Router execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(Names.ROLE);
        if (role == User.Role.ADMIN) {
            String userId = request.getParameter(Names.USER_ID);
            userId = userId.replaceAll("[^0-9]","");
            int uid = Integer.parseInt(userId);
            try {
                boolean result = UserService.blockUser(uid);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(Names.USER_ID, uid);
                    jsonObject.put(Names.RESULT, result);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (ProjectException | IOException e) {
                LOG.error("Blocking user error", e);
                response.sendError(500);
            }
        } else {
            LOG.error("Invalid user role");
            response.sendError(403);
        }
        return null;
    }
}
