const express = require('express');
const router = express.Router();
const authMiddleware = require('../middleware/auth');
const orderService = require('../services/orderService');

router.get('/api/v1/users/orders', authMiddleware.authenticate, async (req, res) => {
    try {
        const page = parseInt(req.query.page) || 1;
        const size = parseInt(req.query.size) || 10;
        
        const userId = req.user.id; 

        const data = await orderService.getOrdersHistory(userId, page, size);

        res.status(200).json(data);

    } catch (error) {
        console.error('Erro ao consultar pedidos:', error);
        res.status(500).json({ 
            erro: { 
                codigo: "ERRO_INTERNO", 
                detalhe: "Ocorreu um erro interno no servidor." 
            } 
        });
    }
});

module.exports = router;