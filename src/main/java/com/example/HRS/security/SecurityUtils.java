package com.example.HRS.security;

import com.auth0.jwt.algorithms.Algorithm;

public class SecurityUtils {
    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256("jfdkgelwhasqytpmcnrxzbuvo".getBytes());
    }
}
