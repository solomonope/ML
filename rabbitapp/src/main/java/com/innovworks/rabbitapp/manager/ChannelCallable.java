/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innovworks.rabbitapp.manager;

import com.rabbitmq.client.Channel;

/**
 *
 * @author Folorunsho Solomon
 */
public interface ChannelCallable<T> {
    String getDescription();
    
}
