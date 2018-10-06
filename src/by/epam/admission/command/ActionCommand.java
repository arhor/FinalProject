/*
 * class: ActionCommand
 */

package by.epam.admission.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Burishinets Maxim
 * @version 1.0 03 Sep 2018
 */
public interface ActionCommand {
    Router execute(HttpServletRequest request, HttpServletResponse response);
}
