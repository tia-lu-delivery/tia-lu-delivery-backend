package br.com.foodelivery.tialudeliveryback.controller;

import br.com.foodelivery.tialudeliveryback.domain.PaymentMethod;
import br.com.foodelivery.tialudeliveryback.repository.PaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;