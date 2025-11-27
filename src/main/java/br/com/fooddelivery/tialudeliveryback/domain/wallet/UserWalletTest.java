@Test
public void naoDevePermitirDefinirPrincipalSeInativo() {
    UserWallet wallet = new UserWallet(new UserId("u1"));

    PaymentMethod pm = new PaymentMethod(new PaymentMethodId("p1"), false);
    wallet.addMethod(pm);

    assertThrows(PaymentMethodInactiveException.class, () -> {
        wallet.definePrincipal("p1");
    });
}
