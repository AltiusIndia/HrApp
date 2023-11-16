/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc.altius.hrApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cc.altius.hrApplication.security.CustomUserDetailsService;

/**
 *
 * @author Akil Mahimwala
 */
@Configuration
//@EnableWebSecurity
public class NewSpringBootSecurity {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(e
                        -> e
                        .accessDeniedPage("/errors/accessDenied.htm"))
                .formLogin(form
                        -> form
                        .loginPage("/home/login.htm").defaultSuccessUrl("/home/index.htm")
                        .permitAll()
                )
                .logout(out
                        -> out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/home/login.htm")
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(t -> t
                .requestMatchers("/assets/**", "/audio/**", "/img/**", "/js/**", "/images/**", "/css/**", "/favicon.ico", "/WEB-INF/jsp/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                .requestMatchers("/home/l.htm**").permitAll()
                //                .requestMatchers("/admin/updateExpiredPassword.htm**").hasAuthority("ROLE_BF_PASSWORD_EXPIRED")
                //                .requestMatchers("/home/index.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                //                .requestMatchers("/ajax/getCanCreateRoleListForRoleId.htm**").hasAuthority("ROLE_BF_CREATE_USER")
                //                .requestMatchers("/ajax/getStateAndCityForZipCode.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                //                .requestMatchers("/ajax/getCurrencyByCurrencyId.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                //                .requestMatchers("/ajax/getClientByClientId.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/ajax/getClientList.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/ajax/updateNextFollowUpDate.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/client/billingNotes.htm**").hasAuthority("ROLE_BF_ADD_CLIENT")
                //                .requestMatchers("/client/addClient.htm**").hasAuthority("ROLE_BF_ADD_CLIENT")
                //                .requestMatchers("/client/listClient.htm**").hasAuthority("ROLE_BF_VIEW_CLIENT")
                //                .requestMatchers("/client/showEditClient.htm**").hasAuthority("ROLE_BF_ADD_CLIENT")
                //                .requestMatchers("/client/editClient.htm**").hasAuthority("ROLE_BF_ADD_CLIENT")
                //                .requestMatchers("/client/viewClient.htm**").hasAuthority("ROLE_BF_VIEW_CLIENT")
                //                .requestMatchers("/company/addCompany.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/company/listCompany.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/company/showEditCompany.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/company/editCompany.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/report/reportSalaryCosting.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/reportSalaryCostingDetails.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/reportSalaryCostingDetailsSubmit.htm**").hasAnyAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/report/reportSalaryCostingDetailsForProcessList.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/reportFullCosting.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/fullCostingDetailsSubmit.htm**").hasAnyAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/report/reportFullCostingOverTime.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/reportCreditNoteSummary.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/reportCreditNoteDetails.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/report/vendorPaymentSummary.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/vendorPayment/editVendorPayment.htm**").hasAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/vendorPayment/updateVendorPayment.htm**").hasAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/currency/addCurrency.htm**").hasAuthority("ROLE_BF_ADD_CURRENCY")
                //                .requestMatchers("/currency/listCurrency.htm**").hasAuthority("ROLE_BF_VIEW_CURRENCY")
                //                .requestMatchers("/currency/showEditCurrency.htm**").hasAuthority("ROLE_BF_ADD_CURRENCY")
                //                .requestMatchers("/currency/editCurrency.htm**").hasAuthority("ROLE_BF_ADD_CURRENCY")
                //                .requestMatchers("/ledger/currentBalanceReport.htm**").hasAuthority("ROLE_BF_BALANCE_REPORT")
                //                .requestMatchers("/ledger/currentBalanceExcel.htm**").hasAuthority("ROLE_BF_BALANCE_REPORT")
                //                .requestMatchers("/ledger/showFollowupNotes.htm**").hasAuthority("ROLE_BF_BALANCE_REPORT")
                //                .requestMatchers("/ledger/updateFollowupNotes.htm**").hasAuthority("ROLE_BF_BALANCE_REPORT")
                //                .requestMatchers("/invoice/addInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/viewInvoice.htm**").hasAuthority("ROLE_BF_VIEW_INVOICE")
                //                .requestMatchers("/invoice/showEditInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/showDeleteInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/deleteInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/editInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/fileDelete.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/fileDownload.htm").hasAnyAuthority("ROLE_BF_VIEW_INVOICE", "ROLE_BF_APPROVE_VENDOR_PAYMENT", "ROLE_BF_ADD_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/invoice/invoiceList.htm**").hasAuthority("ROLE_BF_VIEW_INVOICE")
                //                .requestMatchers("/invoice/invoiceExcelDownload.htm**").hasAuthority("ROLE_BF_VIEW_INVOICE")
                //                .requestMatchers("/invoice/invoicePeriodList.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/invoice/invoicePeriodSummary.htm**").hasAnyAuthority("ROLE_BF_COSTING", "ROLE_BF_ACL_COSTING")
                //                .requestMatchers("/invoice/invoicePeriodSummaryExcel.htm**").hasAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/invoice/invoicePeriodDetailsForProcessGroup.htm**").hasAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/invoice/invoicePeriodDetailsForProcessGroupExcel.htm**").hasAuthority("ROLE_BF_COSTING")
                //                .requestMatchers("/invoice/invoiceTallyList.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/invoiceSummary.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/invoice/dashboard.htm**").hasAuthority("ROLE_BF_BALANCE_REPORT")
                //                .requestMatchers("/invoice/invoiceCount.htm**").hasAuthority("ROLE_BF_VIEW_INVOICE")
                //                .requestMatchers("/ledger/getLedger.htm**").hasAuthority("ROLE_BF_VIEW_LEDGER")
                //                .requestMatchers("/ledger/getLedgerExcel.htm**").hasAuthority("ROLE_BF_VIEW_LEDGER")
                //                .requestMatchers("/ledger/updateGroup.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/payment/paymentStepOne.htm**").hasAuthority("ROLE_BF_MAKE_PAYMENT")
                //                .requestMatchers("/payment/paymentList.htm**").hasAuthority("ROLE_BF_VIEW_PAYMENT")
                //                .requestMatchers("/payment/showEditPayment.htm**").hasAuthority("ROLE_BF_MAKE_PAYMENT")
                //                .requestMatchers("/payment/savePayment.htm**").hasAuthority("ROLE_BF_MAKE_PAYMENT")
                //                .requestMatchers("/payment/viewPayment.htm**").hasAuthority("ROLE_BF_VIEW_PAYMENT")
                //                .requestMatchers("/admin/reloadApplicationLayer.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/report/reportAccessLogExcel.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/report/reportAccessLog.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/admin/userAdd.htm**").hasAuthority("ROLE_BF_ADD_USER")
                //                .requestMatchers("/admin/showUserEdit.htm**").hasAuthority("ROLE_BF_ADD_USER")
                //                .requestMatchers("/admin/userEdit.htm**").hasAuthority("ROLE_BF_ADD_USER")
                //                .requestMatchers("/admin/userList.htm**").hasAuthority("ROLE_BF_VIEW_USER")
                //                .requestMatchers("/admin/userFailedAttemptsReset.htm**").hasAuthority("ROLE_BF_ADD_USER")
                //                .requestMatchers("/home/changePassword.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                //                .requestMatchers("/admin/canCreateRoles.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/vendor/createVendor.htm**").hasAuthority("ROLE_BF_ADD_VENDOR")
                //                .requestMatchers("/vendor/listVendor.htm**").hasAuthority("ROLE_BF_VIEW_VENDOR")
                //                .requestMatchers("/vendor/showEditVendor.htm**").hasAuthority("ROLE_BF_ADD_VENDOR")
                //                .requestMatchers("/vendor/updateVendor.htm**").hasAuthority("ROLE_BF_ADD_VENDOR")
                //                .requestMatchers("/vendorPayment/expenseHeads.htm**").hasAuthority("ROLE_BF_ADD_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/createVendorPayment.htm**").hasAuthority("ROLE_BF_ADD_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/cancelVendorPayment.htm**").hasAuthority("ROLE_BF_ADD_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/approveVendorPayment.htm**").hasAuthority("ROLE_BF_APPROVE_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/viewVendorPaymentDetails.htm").hasAnyAuthority("ROLE_BF_ADD_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/vendorPayment/updateApprovedVendorPayment.htm**").hasAuthority("ROLE_BF_APPROVE_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/createVendorPaymentBatch.htm**").hasAuthority("ROLE_BF_CREATE_BATCH")
                //                .requestMatchers("/vendorPayment/viewBatchDetails.htm**").hasAuthority("ROLE_BF_CREATE_BATCH")
                //                .requestMatchers("/vendorPayment/fundVendorPaymentBatch.htm**").hasAuthority("ROLE_BF_PROVIDE_FUNDS")
                //                .requestMatchers("/vendorPayment/processVendorPayment.htm**").hasAuthority("ROLE_BF_PROCESS_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/updateProcessedVendorPayment.htm**").hasAuthority("ROLE_BF_PROCESS_VENDOR_PAYMENT")
                //                .requestMatchers("/vendorPayment/listVendorPaymentBatch.htm**").hasAuthority("ROLE_BF_CREATE_BATCH")
                //                .requestMatchers("/vendorPayment/listVendorPayment.htm").hasAnyAuthority("ROLE_BF_ADD_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/vendorPayment/listVendorPaymentForPeriod.htm").hasAnyAuthority("ROLE_BF_ADD_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/vendorPayment/pendingTasks.htm").hasAnyAuthority("ROLE_BF_APPROVE_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/vendorPayment/vendorPaymentStats.htm").hasAnyAuthority("ROLE_BF_APPROVE_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/vendorPayment/getVendorPaymentsForUser.htm").hasAnyAuthority("ROLE_BF_APPROVE_VENDOR_PAYMENT", "ROLE_BF_VIEW_ALL_VP")
                //                .requestMatchers("/invoicingData/createInvoicingDataRequest.htm**").hasAuthority("ROLE_BF_INV_DATA_REQUEST")
                //                .requestMatchers("/invoicingData/cancelInvoicingDataRequest.htm**").hasAuthority("ROLE_BF_INV_DATA_REQUEST")
                //                .requestMatchers("/invoicingData/listInvoicingDataRequest.htm**").hasAuthority("ROLE_BF_INV_DATA_REQUEST")
                //                .requestMatchers("/invoicingData/viewInvoicingDataRequest.htm**").hasAuthority("ROLE_BF_INV_DATA_REQUEST")
                //                .requestMatchers("/invoicingData/updateInvoicingDataRequest.htm**").hasAuthority("ROLE_BF_INV_DATA_REQUEST")
                //                .requestMatchers("/invoicingData/approveInvoicingData.htm**").hasAuthority("ROLE_BF_APPROVE_INV_DATA")
                //                .requestMatchers("/invoicingData/updateInvoicingData.htm**").hasAuthority("ROLE_BF_APPROVE_INV_DATA")
                //                .requestMatchers("/invoicingData/listInvoicingData.htm**").hasAuthority("ROLE_BF_VIEW_INV_DATA")
                //                .requestMatchers("/invoicingData/viewInvoicingDataDetails.htm**").hasAuthority("ROLE_BF_VIEW_INV_DATA")
                //                .requestMatchers("/invoicingData/invoiceStatusList.htm**").hasAuthority("ROLE_BF_INVOICE_STATUS")
                //                .requestMatchers("/invoicingData/updateInvoiceStatus.htm**").hasAuthority("ROLE_BF_INVOICE_STATUS")
                //                .requestMatchers("/invoicingData/crmCallStatusList.htm**").hasAuthority("ROLE_BF_CRM_CALL_STATUS")
                //                .requestMatchers("/invoicingData/updateCrmCallStatus.htm**").hasAuthority("ROLE_BF_CRM_CALL_STATUS")
                //                .requestMatchers("/invoicingData/pendingTasks.htm").hasAnyAuthority("ROLE_BF_VIEW_INV_DATA", "ROLE_BF_VIEW_ALL_ID")
                //                .requestMatchers("/invoicingData/invoicingDataStats.htm").hasAnyAuthority("ROLE_BF_APPROVE_INV_DATA", "ROLE_BF_INVOICE_STATUS", "ROLE_BF_CRM_CALL_STATUS")
                //                .requestMatchers("/toDo/createToDoMaster.htm").hasAnyAuthority("ROLE_BF_TODO_MASTER", "ROLE_BF_TODO_MASTER_ALL")
                //                .requestMatchers("/toDo/editToDoMaster.htm").hasAnyAuthority("ROLE_BF_TODO_MASTER", "ROLE_BF_TODO_MASTER_ALL")
                //                .requestMatchers("/toDo/cancelToDoRequest.htm").hasAnyAuthority("ROLE_BF_TODO_MASTER", "ROLE_BF_TODO_MASTER_ALL")
                //                .requestMatchers("/toDo/updateToDoMaster.htm").hasAnyAuthority("ROLE_BF_TODO_MASTER", "ROLE_BF_TODO_MASTER_ALL")
                //                .requestMatchers("/toDo/listToDoMaster.htm").hasAnyAuthority("ROLE_BF_TODO_MASTER", "ROLE_BF_TODO_MASTER_ALL")
                //                .requestMatchers("/toDo/toDoList.htm").hasAnyAuthority("ROLE_BF_TODO", "ROLE_BF_VIEW_TODO_ALL")
                //                .requestMatchers("/toDo/updateToDo.htm**").hasAuthority("ROLE_BF_TODO")
                //                .requestMatchers("/toDo/toDoStats.htm").hasAnyAuthority("ROLE_BF_TODO", "ROLE_BF_VIEW_TODO_ALL")
                //                .requestMatchers("/fmsUpdate.htm**").hasAuthority("ROLE_BF_ADMIN")
                //                .requestMatchers("/followUp/createFollowUp.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/followUp/activeFollowUp.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/followUp/showNextFollowUpDate.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/followUp/updateNextFollowUpDate.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/followup/deleteInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")
                //                .requestMatchers("/followup/addInvoice.htm**").hasAuthority("ROLE_BF_ADD_INVOICE")

                .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.customUserDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
//        final OidcUserService delegate = new OidcUserService();
//        return (userRequest) -> {
//            // Delegate to the default implementation for loading a user
//            OidcUser oidcUser = delegate.loadUser(userRequest);
//            CustomUserDetails curUser = this.customUserDetailsService.loadUserByUsername(oidcUser.getEmail());
//            if (!curUser.isActive()) {
//                throw new org.springframework.security.authentication.AccountExpiredException("User is disabled");
//            }
//            curUser.setOidcUser(oidcUser);
//            return curUser;
//        };
//    }
}
