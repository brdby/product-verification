package ru.brdby.cinderella.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Random;

@Service
public class RSAService {

    private static int BIT_LENGTH = 128;

    private BigInteger N;
    private BigInteger e;
    private BigInteger d;

    @PostConstruct
    public void init() {
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH, r);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH, r);
        N = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(BIT_LENGTH / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public byte[] encrypt(byte[] input){
        BigInteger M = new BigInteger(input);
        return M.modPow(e, N).toByteArray();
    }

    public byte[] decrypt(byte[] input){
        BigInteger M = new BigInteger(input);
        return M.modPow(d, N).toByteArray();
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
