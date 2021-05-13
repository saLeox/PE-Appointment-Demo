local product = KEYS[1]
local qty = KEYS[2]

local fund_key = 'availability_fund_' .. product
local investor_key = 'availabilit_investor_' .. product

local fund = redis.call('GET', fund_key)
local investor = redis.call('GET', investor_key)

if not fund or not investor then
	return -1
end

if tonumber(fund) <= 0 or tonumber(investor) <= 0 then
	return -1
end

if tonumber(fund) - tonumber(qty) < 0 then
	return -1
end

local minuend_fund = 0 - tonumber(qty)
redis.call('DECRBY', investor_key, 1)
redis.call('INCRBYFLOAT', fund_key, minuend_fund)

return 1