USE [master]
GO
/****** Object:  Database [PTIT_SQA_EMS]    Script Date: 2/24/2020 11:56:56 PM ******/
CREATE DATABASE [PTIT_SQA_EMS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PTIT_SQA_EMS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLSERVERBTANH1\MSSQL\DATA\PTIT_SQA_EMS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PTIT_SQA_EMS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLSERVERBTANH1\MSSQL\DATA\PTIT_SQA_EMS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PTIT_SQA_EMS] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PTIT_SQA_EMS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ARITHABORT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET RECOVERY FULL 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  MULTI_USER 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PTIT_SQA_EMS', N'ON'
GO
ALTER DATABASE [PTIT_SQA_EMS] SET QUERY_STORE = OFF
GO
USE [PTIT_SQA_EMS]
GO
/****** Object:  Table [dbo].[AccountObject]    Script Date: 2/24/2020 11:56:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountObject](
	[PhoneNumber] [nvarchar](255) NULL,
	[AccountObjectName] [nvarchar](100) NOT NULL,
	[AccountAddress] [nvarchar](255) NULL,
	[AccountObjectCode] [nvarchar](20) NULL,
	[AccountObjectID] [int] NOT NULL,
	[ClockID] [int] NOT NULL,
	[AccountObjectType] [int] NOT NULL,
	[ContractID] [int] NULL,
 CONSTRAINT [PK_AccountObject] PRIMARY KEY CLUSTERED 
(
	[AccountObjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clock]    Script Date: 2/24/2020 11:56:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clock](
	[ClockID] [int] NOT NULL,
	[ClockCode] [nvarchar](20) NOT NULL,
	[ClockName] [nvarchar](255) NULL,
 CONSTRAINT [PK_Clock] PRIMARY KEY CLUSTERED 
(
	[ClockID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ClockDetail]    Script Date: 2/24/2020 11:56:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClockDetail](
	[ClockIndexID] [int] NOT NULL,
	[Month] [int] NOT NULL,
	[ClockIndex] [int] NULL,
	[CreatedDate] [date] NULL,
	[ClockID] [int] NOT NULL,
	[EmployeeID] [int] NULL,
	[PreClockIndexID] [int] NOT NULL,
 CONSTRAINT [PK_ClockDetail] PRIMARY KEY CLUSTERED 
(
	[ClockIndexID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Contract]    Script Date: 2/24/2020 11:56:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contract](
	[ContractName] [nvarchar](255) NULL,
	[ContractCode] [nvarchar](20) NOT NULL,
	[ContractType] [int] NULL,
	[ContractID] [int] NOT NULL,
 CONSTRAINT [PK_Contract] PRIMARY KEY CLUSTERED 
(
	[ContractID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 2/24/2020 11:56:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[State] [int] NULL,
	[CreatedDate] [date] NULL,
	[InvoiceCode] [nvarchar](20) NOT NULL,
	[TotalAmount] [decimal](18, 4) NULL,
	[CustomerID] [int] NOT NULL,
	[ClockIndexID] [int] NOT NULL,
	[InvoiceID] [int] NOT NULL,
	[Month] [int] NULL,
	[Description] [nvarchar](255) NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AccountObject]  WITH CHECK ADD  CONSTRAINT [FK_AccountObject_Clock] FOREIGN KEY([ClockID])
REFERENCES [dbo].[Clock] ([ClockID])
GO
ALTER TABLE [dbo].[AccountObject] CHECK CONSTRAINT [FK_AccountObject_Clock]
GO
ALTER TABLE [dbo].[AccountObject]  WITH CHECK ADD  CONSTRAINT [FK_AccountObject_Contract] FOREIGN KEY([ContractID])
REFERENCES [dbo].[Contract] ([ContractID])
GO
ALTER TABLE [dbo].[AccountObject] CHECK CONSTRAINT [FK_AccountObject_Contract]
GO
ALTER TABLE [dbo].[ClockDetail]  WITH CHECK ADD  CONSTRAINT [FK_ClockDetail_AccountObject] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[AccountObject] ([AccountObjectID])
GO
ALTER TABLE [dbo].[ClockDetail] CHECK CONSTRAINT [FK_ClockDetail_AccountObject]
GO
ALTER TABLE [dbo].[ClockDetail]  WITH CHECK ADD  CONSTRAINT [FK_ClockDetail_Clock] FOREIGN KEY([ClockID])
REFERENCES [dbo].[Clock] ([ClockID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ClockDetail] CHECK CONSTRAINT [FK_ClockDetail_Clock]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_AccountObject] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[AccountObject] ([AccountObjectID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_AccountObject]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_ClockDetail] FOREIGN KEY([ClockIndexID])
REFERENCES [dbo].[ClockDetail] ([ClockIndexID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_ClockDetail]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số điện thoại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'PhoneNumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'đồng hồ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'ClockID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0-khách hàng,1-Nhân viên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hợp đồng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'ContractID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 - điện dân sinh, 1 - điện sản xuất, 2-điện kinh doanh' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contract', @level2type=N'COLUMN',@level2name=N'ContractType'
GO
USE [master]
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  READ_WRITE 
GO
