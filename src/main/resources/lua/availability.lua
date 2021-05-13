local product = KEYS[1]
local qty = KEYS[2]

local fund_key = 'availability_fund_' + product
local investor_key = 'availabilit_investor_' + product

local fund = redis.call('GET', fund_key)
local investor = redis.call('GET', investor_key)

if not fund or not investor or tonumber(fund) <= 0 or tonumber(investor) then
	return -1
end

if tonumber(fund) - tonumber(qty) < 0 then
	return -1
end

if tonumber(investor) - 1 < 0 then
	return -1
end

redis.call('incrBy', fund_key, 0 - tonumber(qty))
redis.call('incrBy', investor_key, -1)

return 1