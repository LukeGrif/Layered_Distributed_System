# 🧰 Distributed Systems Assignment – Job Marketplace

**University of Limerick – CE4208 Distributed Systems**  
**Contributors:**  
- Luke Griffin (21334528)  
- James Martin (21305277)  
- Shane Ginty (21234442)  
- Aaron Smith (21335168)  

---

## 📖 Introduction

This project implements a layered **Jakarta EE 10** web application simulating an **Online Job Marketplace**, with distinct roles for:

- **Job Providers** who post and manage job offers.
- **Freelancers** who apply for and complete jobs to earn virtual tokens.
- **Administrators** who manage users and posted jobs.

Key technologies include:
- Enterprise JavaBeans (EJB)
- Java Persistence API (JPA)
- JavaServer Faces (JSF)
- Managed beans (request/session scope)
- JSF templating, converters, and validators

![image](https://github.com/user-attachments/assets/52b868ad-ca19-4536-8f84-bb482809bb55)

---

## 🗄️ Database Structure

- **Job ↔ Freelancer** — One-to-Many  
All entities (`Job`, `Freelancer`, `Provider`, `Offer`, `Admin`, `BaseUsers`) are persisted using JPA.

![image](https://github.com/user-attachments/assets/85b6c61d-466a-4d0f-b924-ee810f2ccee1)

---

## 🌐 Web Application Flow

1. User logs in → Role identified via JSF.
2. JSF templates handle dynamic content with consistent layout using `header.xhtml`, `footer.xhtml`, and role-specific sidebars.
3. Managed beans populate UI elements with data dynamically.

---

## 🧑‍💼 Provider Features (6)

1. **Create Job** via `createJob.xhtml`.
2. **List Jobs by Status**: Open (1), Assigned (2), Completed (3).
3. **Remove Open Job**: Only jobs with status = 1.
4. **View Applicants** and their profiles (`ProviderApplications.xhtml`).
5. **Accept Freelancer** using `JobService.assignFreelancerToJob()`.
6. **Mark Job Completed** triggering payment transfer.

![image](https://github.com/user-attachments/assets/2e5ab6a6-f76a-4438-baa0-123cc0c55bb4)

---

## 🧑‍🔧 Freelancer Features (7)

- Homepage contains:
  - **Find Jobs**
  - **View Jobs**
  - **Profile View**

Functional Features:
1. Browse all open jobs (`JobService.listOpenJobs`).
2. Search by keyword (`JobSearchBean.searchByKeyword()`).
3. Search by Job ID (`JobSearchBean.searchById()`).
4. Apply to job (creates an entry in `Offer` table).
5. Revoke application (`OfferService.revokeOffer()`).
6. Complete job → triggers payment balance update.
7. Edit and update profile data.

![image](https://github.com/user-attachments/assets/b24c8d9c-6cf0-4eb2-964a-593a296763b9)

---

## 🛠️ Admin Features (5)

1. Register freelancers (`AdminService.registerFreelancer()`).
2. Remove freelancers (`AdminService.removeFreelancer()`).
3. Register providers (`AdminService.registerProvider()`).
4. Remove providers (`AdminService.removeProvider()`).
5. Remove jobs regardless of status.

![image](https://github.com/user-attachments/assets/b2cb11ab-c865-438a-b99d-a88ce1e87bd4)

---

## 📑 Logging

All key events are logged to `marketplace-app.log`:
- Job acceptances by providers
- Job completions by freelancers

Setup uses `LoggingServletContextListener` and `java.util.logging.FileHandler`.

---

## ⚙️ Technical Requirements

- ✅ **JPA Entities**: All data models persisted.
- ✅ **Stateless Session Beans**: `AdminService`, `JobService`, `OfferService`, `UserService`.
- ✅ **RequestScoped Beans**: `FindJobsBean`, `CurrentJobsBean`, etc.
- ✅ **SessionScoped Beans**: `LoginBean`, `JobBean`, `AdminBean`.
- ✅ **Composite Component**: Common footer.
- ✅ **Custom Converter**: `JobConverter` for ID/entity mapping.
- ✅ **Custom Validator**: `PositivePaymentValidator` for positive numbers.
- ✅ **JSF Templates** on all pages.
- ✅ **JSF Snippet** reused in at least two pages.
- ✅ **SQL Injection Prevention**: All queries use parameterized JPQL.

---

## ✅ Conclusion

This layered Jakarta EE application satisfies all specified functional and technical requirements, delivering a robust online job marketplace platform. It supports user-role-based workflows, data persistence, logging, and secure interactions.
