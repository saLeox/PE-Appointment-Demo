package com.gof.springcloud.constants;

public class Constants {
	public static final String APPOINTMENT_STATUS_PENDING = "pending";
	public static final String APPOINTMENT_STATUS_SUCCESS = "Success";
	public static final String APPOINTMENT_STATUS_FAILURE = "Failure";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";

    // workflow key, initiate and get when workflow is defined.
    public static final Integer WORKFLOW_KEY_APPOINTMENT = 32;
    public static final String OPERATE_TYPE_PE_APPOINTMENT = "PE-appointment";
    // status for workflow
    public static final String WORKFLOW_STATUS_SAVE = "save";
    public static final String WORKFLOW_STATUS_PENDING = "pending";

    // redis
	public static final String REDIS_LUA_SCRIPT_AVAILABILITY = "lua/availability.lua";
	public static final String REDIS_KEY_PREFIX_AVAILABILITY_FUND = "availability_fund_";
	public static final String REDIS_KEY_PREFIX_AVAILABILITY_INVESTOR = "availabilit_investor_";


}
