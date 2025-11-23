const db = require('../data/database'); 

const orderService = {
    async getOrdersHistory(userId, page, size) {
        const offset = (page - 1) * size;

        const allUserOrders = await db.query(
            `SELECT * FROM orders WHERE user_id = ? ORDER BY data_abertura DESC`, [userId]
        );
        
        const paginatedOrders = allUserOrders.slice(offset, offset + size);

        const pedidosFormatados = paginatedOrders.map(pedido => ({
            numero_pedido: pedido.order_number, 
            data_abertura: pedido.opened_at,    
            status_pedido: pedido.status,       
            nome_restaurante: pedido.restaurant_name, 
            valor_total: parseFloat(pedido.total_value).toFixed(2) 
        }));

        return {
            page: page,
            size: size,
            total_pedidos: allUserOrders.length, 
            pedidos: pedidosFormatados
        };
    }
};

module.exports = orderService;